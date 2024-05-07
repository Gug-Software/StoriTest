package com.jkgug.example.storitest.repository.remote.movement

import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface BankMovementsRepository {

    /**
     * Get movements data from server
     */
    suspend fun getMovementsData(): Flow<NetworkResult<Any?>>

}
