package com.jkgug.example.storitest.data.repository.details

import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface MovementDetailsRepository {

    suspend fun getMovementDetails(
        movementId: String
    ): Flow<NetworkResult<Any?>>

}
