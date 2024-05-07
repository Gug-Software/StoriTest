package com.jkgug.example.storitest.di

import com.jkgug.example.storitest.repository.local.sesion.SessionRepository
import com.jkgug.example.storitest.repository.local.sesion.SessionRepositoryImpl
import com.jkgug.example.storitest.repository.local.userdata.UserDataRepository
import com.jkgug.example.storitest.repository.local.userdata.UserDataRepositoryImpl
import org.koin.dsl.module

class LocalRepositoriesModule {

    companion object {

        val localRepositoriesModule = module {

            single<UserDataRepository> {
                UserDataRepositoryImpl(
                    sharedPreferences = get()
                )
            }

            single<SessionRepository> {
                SessionRepositoryImpl(
                    sharedPreferences = get()
                )
            }

        }

    }
}