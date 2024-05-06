package com.jkgug.example.storitest.data.repository.signup

import com.jkgug.example.storitest.data.UserData
import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface SignUpRepository {

    /**
     * Register user in firebase with createUserWithEmailAndPassword and update firestore.collection
     * in firestore
     */
    suspend fun createUserWithEmailAndPassword(userData: UserData): Flow<NetworkResult<Any?>>

    suspend fun saveUserData(userData: UserData): Flow<NetworkResult<Any>>

}
