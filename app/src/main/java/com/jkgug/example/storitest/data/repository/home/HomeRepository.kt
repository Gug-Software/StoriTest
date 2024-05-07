package com.jkgug.example.storitest.data.repository.home

import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    /**
     * Get user local data
     */
    fun getUserLocalData(): String

    /**
     * Get movements data from server
     */
    suspend fun getMovementsData(): Flow<NetworkResult<Any?>>

    /**
     * Logout user
     */
    fun logout()

}
