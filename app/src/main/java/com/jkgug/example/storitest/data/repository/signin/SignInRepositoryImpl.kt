package com.jkgug.example.storitest.data.repository.signin

import com.google.firebase.auth.FirebaseAuth
import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class SignInRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
) : SignInRepository {

    override suspend fun signInWithEmailAndPassword(
        userMail: String,
        userPassword: String
    ): Flow<NetworkResult<Any?>> =
        callbackFlow {
            firebaseAuth.signInWithEmailAndPassword(userMail, userPassword)
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

}
