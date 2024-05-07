package com.jkgug.example.storitest.repository.local.sesion

import android.content.SharedPreferences
import com.jkgug.example.storitest.utils.PREFERENCES_IS_LOGGED

class SessionRepositoryImpl(
    private val sharedPreferences: SharedPreferences
) : SessionRepository {
    override suspend fun saveIsLogged(isLogged: Boolean) {
        sharedPreferences.edit().putBoolean(PREFERENCES_IS_LOGGED, isLogged).apply()
    }

    override fun isUserLogged(): Boolean {
        return sharedPreferences.getBoolean(PREFERENCES_IS_LOGGED, false)
    }

}
