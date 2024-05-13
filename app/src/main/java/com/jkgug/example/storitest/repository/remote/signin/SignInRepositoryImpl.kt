package com.jkgug.example.storitest.repository.remote.signin

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
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class SignInRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : SignInRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun signInWithEmailAndPassword(
        userMail: String,
        userPassword: String
    ): Flow<NetworkResult<Any?>> {
        return callbackFlow {
            signInWithEmailAndPassword(userMail, userPassword)
        }
            .filter { it is NetworkResult.Success }
            .flatMapConcat { getUserDataFireStore(it.data as String) }
            .map { it }
            .flowOn(dispatcher)
    }

    private suspend fun ProducerScope<NetworkResult<Any?>>.signInWithEmailAndPassword(
        userMail: String,
        userPassword: String
    ) {
        firebaseAuth.signInWithEmailAndPassword(userMail, userPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    trySend(NetworkResult.Success(task.result.user?.uid.orEmpty()))
                }
            }
            .addOnFailureListener { trySend(NetworkResult.Error(message = it.message)) }
            .addOnCompleteListener { close() }
        awaitClose()
    }

    /**
     * Get user data from firestore by firebaseUser
     * @param firebaseUserId user id from firebase
     */
    private suspend fun getUserDataFireStore(
        firebaseUserId: String
    ): Flow<NetworkResult<Any?>> = callbackFlow {
        firestore.collection(FIRE_STORE_COLLECTION_USERS).document(
            firebaseUserId
        ).get()
            .addOnSuccessListener { trySend(NetworkResult.Success(it.toObject(UserData::class.java))) }
            .addOnFailureListener { trySend(NetworkResult.Error(message = it.message)) }
            .addOnCompleteListener { close() }
        awaitClose()
    }

}
