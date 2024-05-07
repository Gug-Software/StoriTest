package com.jkgug.example.storitest.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import com.jkgug.example.storitest.data.repository.details.MovementDetailsRepository
import com.jkgug.example.storitest.data.repository.details.MovementDetailsRepositoryImpl
import com.jkgug.example.storitest.data.repository.home.HomeRepository
import com.jkgug.example.storitest.data.repository.home.HomeRepositoryImpl
import com.jkgug.example.storitest.data.repository.logged.LoggedRepository
import com.jkgug.example.storitest.data.repository.logged.LoggedRepositoryImpl
import com.jkgug.example.storitest.data.repository.signin.SignInRepository
import com.jkgug.example.storitest.data.repository.signin.SignInRepositoryImpl
import com.jkgug.example.storitest.data.repository.signup.SignUpRepository
import com.jkgug.example.storitest.data.repository.signup.SingUpRepositoryImpl
import com.jkgug.example.storitest.ui.screen.details.MovementDetailsViewModel
import com.jkgug.example.storitest.ui.screen.home.HomeViewModel
import com.jkgug.example.storitest.ui.screen.navhost.NavHostViewModel
import com.jkgug.example.storitest.ui.screen.signin.SignInViewModel
import com.jkgug.example.storitest.ui.screen.signup.SignUpViewModel
import com.jkgug.example.storitest.utils.PREFERENCES_FILE_KEY
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class StoriTestModule {

    companion object {

        val storiTestModule = module {

            // Firebase
            single { FirebaseAuth.getInstance() }
            single { Firebase.firestore }
            single { provideSettingsPreferences(androidApplication()) }

            // Repository
            single<SignUpRepository> {
                SingUpRepositoryImpl(firebaseAuth = get(), firestore = get())
            }
            single<SignInRepository> {
                SignInRepositoryImpl(
                    firebaseAuth = get(),
                    firestore = get(),
                    sharedPreferences = get()
                )
            }
            single<HomeRepository> {
                HomeRepositoryImpl(
                    firestore = get(),
                    sharedPreferences = get()
                )
            }
            single<MovementDetailsRepository> {
                MovementDetailsRepositoryImpl(
                    firestore = get()
                )
            }
            single<LoggedRepository> {
                LoggedRepositoryImpl(
                    sharedPreferences = get()
                )
            }

            // viewmodel
            viewModel {
                SignUpViewModel(signUpRepository = get())
            }
            viewModel {
                SignInViewModel(signInRepository = get())
            }
            viewModel {
                HomeViewModel(homeRepository = get())
            }
            viewModel {
                MovementDetailsViewModel(
                    savedStateHandle = get(),
                    movementDetailsRepository = get()
                )
            }
            viewModel {
                NavHostViewModel(
                    loggedRepository = get()
                )
            }

        }

        private fun provideSettingsPreferences(app: Application): SharedPreferences =
            app.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)

    }

}