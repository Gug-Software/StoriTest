package com.jkgug.example.storitest.repository.remote.signin

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.jkgug.example.storitest.domain.UserData
import com.jkgug.example.storitest.utils.FIRE_STORE_COLLECTION_USERS
import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class SignInRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
) : SignInRepository {

    override suspend fun signInWithEmailAndPassword(
        userMail: String,
        userPassword: String
    ): Flow<NetworkResult<Any?>> =
        callbackFlow {
            firebaseAuth.signInWithEmailAndPassword(userMail, userPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        trySend(NetworkResult.Success(task.result.user?.uid.orEmpty()))
                        close()
                    }
                }.addOnFailureListener { exception ->
                    trySend(NetworkResult.Error(message = exception.message))
                    close()
                }
            awaitClose()
        }

    override suspend fun getUserDataFireStore(
        firebaseUserId: String
    ): Flow<NetworkResult<Any?>> = callbackFlow {
        firestore.collection(FIRE_STORE_COLLECTION_USERS).document(
            firebaseUserId
        ).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val userData = document.toObject(UserData::class.java)
                    trySend(NetworkResult.Success(userData))
                } else {
                    trySend(NetworkResult.Error(message = "User not found"))
                }
                close()
            }
            .addOnFailureListener {
                trySend(NetworkResult.Error(message = it.message))
                close()
            }
        awaitClose()
    }

}
