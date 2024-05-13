package com.jkgug.example.storitest.repository.remote.movement

import com.google.firebase.firestore.FirebaseFirestore
import com.jkgug.example.storitest.domain.BankMovement
import com.jkgug.example.storitest.utils.FIRE_STORE_COLLECTION_MOVEMENTS
import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class BankMovementDetailsRepositoryImpl(
    private val firestore: FirebaseFirestore
) : BankMovementDetailsRepository {

    override fun getMovementDetails(
        movementId: String
    ): Flow<NetworkResult<Any?>> = callbackFlow {
        firestore
            .collection(FIRE_STORE_COLLECTION_MOVEMENTS)
            .document(movementId)
            .get()
            .addOnSuccessListener { movement ->
                if (movement.exists()) {
                    trySend(NetworkResult.Success(movement.toObject(BankMovement::class.java)))
                } else {
                    trySend(NetworkResult.Error(message = "Movement information not found"))
                }
            }
            .addOnFailureListener { trySend(NetworkResult.Error(message = it.message)) }
            .addOnCompleteListener { close() }
        awaitClose()
    }
}
