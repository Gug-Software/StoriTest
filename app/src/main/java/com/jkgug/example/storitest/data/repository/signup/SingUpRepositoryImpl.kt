package com.jkgug.example.storitest.data.repository.signup

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.jkgug.example.storitest.data.UserData
import com.jkgug.example.storitest.utils.FIRE_STORE_COLLECTION_USERS
import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class SingUpRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : SignUpRepository {

    override suspend fun createUserWithEmailAndPassword(
        userMail: String,
        userPassword: String
    ): Flow<NetworkResult<Any?>> =
        callbackFlow {
            firebaseAuth.createUserWithEmailAndPassword(userMail, userPassword)
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
        val userRef = firestore.collection(FIRE_STORE_COLLECTION_USERS).document(
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
