package com.jkgug.example.storitest.di

import com.jkgug.example.storitest.usecase.SignUpUseCase
import com.jkgug.example.storitest.usecase.SignUpUseCaseImpl
import com.jkgug.example.storitest.usecase.GetBankMovementDetailsRemoteUseCase
import com.jkgug.example.storitest.usecase.GetBankMovementDetailsRemoteUseCaseImpl
import com.jkgug.example.storitest.usecase.GetBankMovementsRemoteUseCase
import com.jkgug.example.storitest.usecase.GetBankMovementsRemoteUseCaseImpl
import com.jkgug.example.storitest.usecase.GetUserNameLocallyUseCase
import com.jkgug.example.storitest.usecase.GetUserNameLocallyUseCaseImpl
import com.jkgug.example.storitest.usecase.IsUserLoggedUseCase
import com.jkgug.example.storitest.usecase.IsUserLoggedUseCaseImpl
import com.jkgug.example.storitest.usecase.LogOutUserUseCase
import com.jkgug.example.storitest.usecase.LogOutUserUseCaseImpl
import com.jkgug.example.storitest.usecase.SaveUserDataLocallyUseCase
import com.jkgug.example.storitest.usecase.SaveUserDataLocallyUseCaseImpl
import com.jkgug.example.storitest.usecase.SignInUseCase
import com.jkgug.example.storitest.usecase.SignInUseCaseImpl
import org.koin.dsl.module

class UseCaseModule {

    companion object {

        val useCaseModule = module {

            single<SignUpUseCase> {
                SignUpUseCaseImpl(
                    signUpRepository = get()
                )
            }

            single<SignInUseCase> {
                SignInUseCaseImpl(
                    signInRepository = get()
                )
            }

            single<SaveUserDataLocallyUseCase> {
                SaveUserDataLocallyUseCaseImpl(
                    userDataRepository = get(),
                    sessionRepository = get()
                )
            }

            single<GetUserNameLocallyUseCase> {
                GetUserNameLocallyUseCaseImpl(
                    userDataRepository = get()
                )
            }

            single<GetBankMovementsRemoteUseCase> {
                GetBankMovementsRemoteUseCaseImpl(
                    bankMovementsRepository = get()

                )
            }

            single<LogOutUserUseCase> {
                LogOutUserUseCaseImpl(
                    sessionRepository = get()
                )
            }

            single<GetBankMovementDetailsRemoteUseCase> {
                GetBankMovementDetailsRemoteUseCaseImpl(
                    bankMovementDetailsRepository = get()
                )
            }

            single<IsUserLoggedUseCase> {
                IsUserLoggedUseCaseImpl(
                    sessionRepository = get()
                )
            }

        }

    }

}