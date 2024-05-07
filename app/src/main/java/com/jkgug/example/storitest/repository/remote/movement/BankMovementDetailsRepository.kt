package com.jkgug.example.storitest.repository.remote.movement

import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface BankMovementDetailsRepository {

    /**
     * get movement details by movement id from firestore
     * @param movementId: the id of the movement
     */
    suspend fun getMovementDetails(
        movementId: String
    ): Flow<NetworkResult<Any?>>

}
