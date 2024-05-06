package com.jkgug.example.storitest.data.repository.home

import android.content.SharedPreferences
import com.google.firebase.firestore.FirebaseFirestore
import com.jkgug.example.storitest.data.BankMovement
import com.jkgug.example.storitest.utils.FIRE_STORE_COLLECTION_MOVEMENTS
import com.jkgug.example.storitest.utils.NetworkResult
import com.jkgug.example.storitest.utils.PREFERENCES_USER_NAME
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class HomeRepositoryImpl(
    private val firestore: FirebaseFirestore,
    private val sharedPreferences: SharedPreferences
) : HomeRepository {

    override fun getUserLocalData(): String {
        return sharedPreferences.getString(PREFERENCES_USER_NAME, "").orEmpty()
    }

    override suspend fun getMovementsData(): Flow<NetworkResult<Any?>> = callbackFlow {
        firestore.collection(FIRE_STORE_COLLECTION_MOVEMENTS).get()
            .addOnSuccessListener { movements ->
                if (movements.isEmpty.not()) {
                    val movementsMutableList = mutableListOf<BankMovement>()
                    movements.forEach {
                        movementsMutableList.add(it.toObject(BankMovement::class.java))
                    }
                    trySend(NetworkResult.Success(movementsMutableList))
                } else {
                    trySend(NetworkResult.Error(message = "Movements information not found"))
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