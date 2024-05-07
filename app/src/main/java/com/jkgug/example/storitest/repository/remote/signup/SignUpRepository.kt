package com.jkgug.example.storitest.repository.remote.signup

import com.jkgug.example.storitest.domain.UserData
import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface SignUpRepository {

    /**
     * Register user in firebase with createUserWithEmailAndPassword
     * @param userMail user email
     * @param userPassword user password
     */
    suspend fun createUserWithEmailAndPassword(
        userMail: String,
        userPassword: String
    ): Flow<NetworkResult<Any?>>

    /**
     * Save user data to firestore
     * @param userData user data
     */
    suspend fun saveUserData(
        userData: UserData
    ): Flow<NetworkResult<Any>>

}
