package com.jkgug.example.storitest.di

import com.jkgug.example.storitest.ui.screen.details.MovementDetailsViewModel
import com.jkgug.example.storitest.ui.screen.home.HomeViewModel
import com.jkgug.example.storitest.ui.screen.navhost.NavHostViewModel
import com.jkgug.example.storitest.ui.screen.signin.SignInViewModel
import com.jkgug.example.storitest.ui.screen.signup.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class ViewModelModule {

    companion object {

        val viewModelModule = module {

            // viewmodel
            viewModel {
                SignUpViewModel(
                    signUpUseCase = get()
                )
            }

            viewModel {
                SignInViewModel(
                    signInRemoteWithEmailAndPasswordUseCase = get(),
                    saveUserDataLocallyUseCase = get()
                )
            }

            viewModel {
                HomeViewModel(
                    getUserNameLocallyUseCase = get(),
                    getBankMovementsRemoteUseCase = get(),
                    logOutUserUseCase = get()
                )
            }

            viewModel {
                MovementDetailsViewModel(
                    savedStateHandle = get(),
                    getBankMovementDetailsRemoteUseCase = get()
                )
            }

            viewModel {
                NavHostViewModel(
                    isUserLoggedUseCase = get()
                )
            }

        }

    }

}