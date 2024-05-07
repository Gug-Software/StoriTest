package com.jkgug.example.storitest.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.jkgug.example.storitest.utils.PREFERENCES_FILE_KEY
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

class PreferencesModule {

    companion object {

        val preferencesModule = module {
            single { provideSettingsPreferences(androidApplication()) }
        }

        private fun provideSettingsPreferences(app: Application): SharedPreferences =
            app.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)

    }

}