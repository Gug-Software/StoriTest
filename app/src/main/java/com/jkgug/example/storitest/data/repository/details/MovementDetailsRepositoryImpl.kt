package com.jkgug.example.storitest.data.repository.details

import com.google.firebase.firestore.FirebaseFirestore
import com.jkgug.example.storitest.data.BankMovement
import com.jkgug.example.storitest.utils.FIRE_STORE_COLLECTION_MOVEMENTS
import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class MovementDetailsRepositoryImpl(
    private val firestore: FirebaseFirestore
) : MovementDetailsRepository {

    override suspend fun getMovementDetails(
        movementId: String
    ): Flow<NetworkResult<Any?>> = callbackFlow {
        firestore.collection(FIRE_STORE_COLLECTION_MOVEMENTS).document(movementId).get()
            .addOnSuccessListener { movement ->
                if (movement.exists()) {
                    trySend(NetworkResult.Success(movement.toObject(BankMovement::class.java)))
                } else {
                    trySend(NetworkResult.Error(message = "Movement information not found"))
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
