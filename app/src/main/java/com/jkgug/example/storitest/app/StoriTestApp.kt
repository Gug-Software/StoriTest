package com.jkgug.example.storitest.app

import android.app.Application
import com.jkgug.example.storitest.di.StoriTestModule.Companion.storiTestModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class StoriTestMainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@StoriTestMainApplication)
            modules(storiTestModule)
        }
    }

}