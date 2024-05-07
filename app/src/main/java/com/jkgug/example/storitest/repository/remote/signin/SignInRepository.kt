package com.jkgug.example.storitest.repository.remote.signin

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

    /**
     * Get user data from firestore by firebaseUser
     * @param firebaseUserId user id from firebase
     */
    suspend fun getUserDataFireStore(
        firebaseUserId: String
    ): Flow<NetworkResult<Any?>>


}
