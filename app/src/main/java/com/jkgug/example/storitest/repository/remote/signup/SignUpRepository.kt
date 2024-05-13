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
    suspend fun signUpUserAndSaveUserData(
        userMail: String,
        userPassword: String,
        userData: UserData
    ): Flow<NetworkResult<Any?>>

}
