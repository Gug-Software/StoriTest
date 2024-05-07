package com.jkgug.example.storitest.data.repository.details

import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface MovementDetailsRepository {

    /**
     * get movement details by movement id from firestore
     * @param movementId: the id of the movement
     */
    suspend fun getMovementDetails(
        movementId: String
    ): Flow<NetworkResult<Any?>>

}
