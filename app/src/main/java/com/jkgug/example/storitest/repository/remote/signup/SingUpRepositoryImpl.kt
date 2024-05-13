package com.jkgug.example.storitest.repository.remote.signup

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.jkgug.example.storitest.domain.UserData
import com.jkgug.example.storitest.utils.FIRE_STORE_COLLECTION_USERS
import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class SingUpRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : SignUpRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun signUpUserAndSaveUserData(
        userMail: String,
        userPassword: String,
        userData: UserData
    ): Flow<NetworkResult<Any?>> {
        return callbackFlow {
            createUserWithEmailAndPassword(userMail, userPassword)
        }
            .flatMapConcat { saveUserData(userData) }
            .map { it }
            .flowOn(dispatcher)
    }

    private suspend fun ProducerScope<NetworkResult<Any?>>.createUserWithEmailAndPassword(
        userMail: String,
        userPassword: String
    ) {
        firebaseAuth.createUserWithEmailAndPassword(userMail, userPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    trySend(NetworkResult.Success(task.result.user))
                }
            }
            .addOnFailureListener { trySend(NetworkResult.Error(message = it.message)) }
            .addOnCompleteListener { close() }
        awaitClose()
    }


    private suspend fun saveUserData(
        userData: UserData
    ): Flow<NetworkResult<Any?>> = callbackFlow {
        val userRef = firestore
            .collection(FIRE_STORE_COLLECTION_USERS)
            .document(firebaseAuth.currentUser?.uid.orEmpty())
        userRef.set(userData)
            .addOnSuccessListener { trySend(NetworkResult.Success(true)) }
            .addOnFailureListener { trySend(NetworkResult.Error(message = it.message)) }
            .addOnCompleteListener { close() }
        awaitClose()
    }

}
