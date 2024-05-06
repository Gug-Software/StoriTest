package com.jkgug.example.storitest.data.repository.signin

import com.jkgug.example.storitest.data.UserData
import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface SignInRepository {

    /**
     * Register user in firebase with createUserWithEmailAndPassword and update firestore.collection
     * in firestore
     */
    suspend fun signInWithEmailAndPassword(
        userMail: String,
        userPassword: String
    ): Flow<NetworkResult<Any?>>

    suspend fun getUserDataFireStore(
        firebaseUser: String
    ): Flow<NetworkResult<Any?>>

    suspend fun saveLocallyUserData(userData: UserData)

}
