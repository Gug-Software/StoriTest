package com.jkgug.example.storitest.data.repository.logged

import android.content.SharedPreferences
import com.jkgug.example.storitest.utils.PREFERENCES_IS_LOGGED

class LoggedRepositoryImpl(
    private val sharedPreferences: SharedPreferences
) : LoggedRepository {

    override fun isUserLogged(): Boolean {
        return sharedPreferences.getBoolean(PREFERENCES_IS_LOGGED, false)
    }

}
