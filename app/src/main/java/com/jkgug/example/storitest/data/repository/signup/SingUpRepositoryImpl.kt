package com.jkgug.example.storitest.data.repository.signup

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.jkgug.example.storitest.data.UserData
import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext

class SingUpRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : SignUpRepository {

    override suspend fun createUserWithEmailAndPassword(
        userData: UserData
    ): Flow<NetworkResult<Any?>> =
        callbackFlow {
            firebaseAuth.createUserWithEmailAndPassword(userData.userMail, userData.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        trySend(NetworkResult.Success(task.result.user))
                        close()
                    }
                }.addOnFailureListener { exception ->
                    trySend(NetworkResult.Error(message = exception.message))
                    close()
                }
            awaitClose()
        }

    override suspend fun saveUserData(
        userData: UserData
    ): Flow<NetworkResult<Any>> = callbackFlow {
        val userRef = firestore.collection("users").document(
            firebaseAuth.currentUser?.uid.orEmpty()
        )
        userRef.set(userData)
            .addOnSuccessListener {
                trySend(NetworkResult.Success(true))
                close()
            }
            .addOnFailureListener {
                trySend(NetworkResult.Error(message = it.message))
                close()
            }
        awaitClose()
    }

}
