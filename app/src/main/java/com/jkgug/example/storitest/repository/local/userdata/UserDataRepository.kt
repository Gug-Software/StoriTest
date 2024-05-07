package com.jkgug.example.storitest.repository.local.userdata

interface UserDataRepository {

    /**
     * Save user data locally
     * @param userName user name
     */
    suspend fun saveLocallyUserData(userName: String)

    /**
     * Get user local data
     */
    fun getLocallyUserName(): String

}
