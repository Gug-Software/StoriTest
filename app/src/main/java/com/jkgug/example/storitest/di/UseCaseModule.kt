package com.jkgug.example.storitest.di

import com.jkgug.example.storitest.usecase.CreateUserRemoteWithEmailAndPasswordUseCase
import com.jkgug.example.storitest.usecase.CreateUserRemoteWithEmailAndPasswordUseCaseImpl
import com.jkgug.example.storitest.usecase.GetBankMovementDetailsRemoteUseCase
import com.jkgug.example.storitest.usecase.GetBankMovementDetailsRemoteUseCaseImpl
import com.jkgug.example.storitest.usecase.GetBankMovementsRemoteUseCase
import com.jkgug.example.storitest.usecase.GetBankMovementsRemoteUseCaseImpl
import com.jkgug.example.storitest.usecase.GetUserDataRemoteUseCase
import com.jkgug.example.storitest.usecase.GetUserDataRemoteUseCaseImpl
import com.jkgug.example.storitest.usecase.GetUserNameLocallyUseCase
import com.jkgug.example.storitest.usecase.GetUserNameLocallyUseCaseImpl
import com.jkgug.example.storitest.usecase.IsUserLoggedUseCase
import com.jkgug.example.storitest.usecase.IsUserLoggedUseCaseImpl
import com.jkgug.example.storitest.usecase.LogOutUserUseCase
import com.jkgug.example.storitest.usecase.LogOutUserUseCaseImpl
import com.jkgug.example.storitest.usecase.SaveUserDataLocallyUseCase
import com.jkgug.example.storitest.usecase.SaveUserDataLocallyUseCaseImpl
import com.jkgug.example.storitest.usecase.SaveUserDataRemoteUseCase
import com.jkgug.example.storitest.usecase.SaveUserDataRemoteUseCaseImpl
import com.jkgug.example.storitest.usecase.SignInRemoteWithEmailAndPasswordUseCase
import com.jkgug.example.storitest.usecase.SignInRemoteWithEmailAndPasswordUseCaseImpl
import org.koin.dsl.module

class UseCaseModule {

    companion object {

        val useCaseModule = module {

            single<CreateUserRemoteWithEmailAndPasswordUseCase> {
                CreateUserRemoteWithEmailAndPasswordUseCaseImpl(
                    signUpRepository = get()
                )
            }

            single<SaveUserDataRemoteUseCase> {
                SaveUserDataRemoteUseCaseImpl(
                    signUpRepository = get()
                )
            }

            single<SignInRemoteWithEmailAndPasswordUseCase> {
                SignInRemoteWithEmailAndPasswordUseCaseImpl(
                    signInRepository = get()
                )
            }

            single<GetUserDataRemoteUseCase> {
                GetUserDataRemoteUseCaseImpl(
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