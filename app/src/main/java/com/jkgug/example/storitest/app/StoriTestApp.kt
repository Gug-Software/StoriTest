package com.jkgug.example.storitest.app

import android.app.Application
import com.jkgug.example.storitest.di.LocalRepositoriesModule.Companion.localRepositoriesModule
import com.jkgug.example.storitest.di.PreferencesModule.Companion.preferencesModule
import com.jkgug.example.storitest.di.RemoteRepositoriesModule.Companion.remoteRepositoriesModule
import com.jkgug.example.storitest.di.SingleModule.Companion.singleModule
import com.jkgug.example.storitest.di.UseCaseModule.Companion.useCaseModule
import com.jkgug.example.storitest.di.ViewModelModule.Companion.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class StoriTestMainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@StoriTestMainApplication)
            modules(
                singleModule,
                preferencesModule,
                localRepositoriesModule,
                remoteRepositoriesModule,
                viewModelModule,
                useCaseModule,
            )
        }
    }

}