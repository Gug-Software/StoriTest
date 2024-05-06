package com.jkgug.example.storitest.data.repository.home

import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun getUserLocalData(): String

    suspend fun getMovementsData(): Flow<NetworkResult<Any?>>

}
