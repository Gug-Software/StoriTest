package com.jkgug.example.storitest.repository.remote.movement

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.jkgug.example.storitest.domain.BankMovement
import com.jkgug.example.storitest.utils.FIRE_STORE_COLLECTION_MOVEMENTS
import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class BankMovementsRepositoryImpl(
    private val firestore: FirebaseFirestore
) : BankMovementsRepository {

    override suspend fun getMovementsData(): Flow<NetworkResult<Any?>> = callbackFlow {
        firestore
            .collection(FIRE_STORE_COLLECTION_MOVEMENTS)
            .get()
            .addOnSuccessListener { movements ->
                val movementsMutableList = mutableListOf<BankMovement>()
                movements.forEach { movementsMutableList.add(bankMovement(it)) }
                trySend(NetworkResult.Success(movementsMutableList))
            }
            .addOnFailureListener { trySend(NetworkResult.Error(message = it.message)) }
            .addOnCompleteListener { close() }
        awaitClose()
    }

    private fun bankMovement(it: QueryDocumentSnapshot): BankMovement {
        val movement = it.toObject(BankMovement::class.java)
        movement.idFromFireStore = it.id
        return movement
    }

}
