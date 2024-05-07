package com.jkgug.example.storitest.repository.local.userdata

import android.content.SharedPreferences
import com.jkgug.example.storitest.utils.PREFERENCES_USER_NAME

class UserDataRepositoryImpl(
    private val sharedPreferences: SharedPreferences
) : UserDataRepository {
    override suspend fun saveLocallyUserData(userName: String) {
        sharedPreferences.edit().putString(PREFERENCES_USER_NAME, userName).apply()
    }

    override fun getLocallyUserName(): String {
        return sharedPreferences.getString(PREFERENCES_USER_NAME, "").orEmpty()
    }

}
