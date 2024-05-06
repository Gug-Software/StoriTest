package com.jkgug.example.storitest.di

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import com.jkgug.example.storitest.data.repository.signin.SignInRepository
import com.jkgug.example.storitest.data.repository.signin.SignInRepositoryImpl
import com.jkgug.example.storitest.data.repository.signup.SignUpRepository
import com.jkgug.example.storitest.data.repository.signup.SingUpRepositoryImpl
import com.jkgug.example.storitest.ui.screen.signin.SignInViewModel
import com.jkgug.example.storitest.ui.screen.signup.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class StoriTestModule {

    companion object {

        val storiTestModule = module {

            // Firebase
            single { FirebaseAuth.getInstance() }
            single { Firebase.firestore }

            // Repository
            single<SignUpRepository> {
                SingUpRepositoryImpl(
                    firebaseAuth = get(),
                    firestore = get(),
                )
            }
            single<SignInRepository> {
                SignInRepositoryImpl(
                    firebaseAuth = get()
                )
            }

            // viewmodel
            viewModel {
                SignUpViewModel(
                    signUpRepository = get()
                )
            }
            viewModel {
                SignInViewModel(
                    signInRepository = get()
                )
            }

        }

    }
}