package com.jkgug.example.storitest.di

import com.jkgug.example.storitest.repository.remote.movement.BankMovementDetailsRepository
import com.jkgug.example.storitest.repository.remote.movement.BankMovementDetailsRepositoryImpl
import com.jkgug.example.storitest.repository.remote.movement.BankMovementsRepository
import com.jkgug.example.storitest.repository.remote.movement.BankMovementsRepositoryImpl
import com.jkgug.example.storitest.repository.remote.signin.SignInRepository
import com.jkgug.example.storitest.repository.remote.signin.SignInRepositoryImpl
import com.jkgug.example.storitest.repository.remote.signup.SignUpRepository
import com.jkgug.example.storitest.repository.remote.signup.SingUpRepositoryImpl
import org.koin.dsl.module

class RemoteRepositoriesModule {

    companion object {

        val remoteRepositoriesModule = module {

            single<SignUpRepository> {
                SingUpRepositoryImpl(
                    firebaseAuth = get(),
                    firestore = get()
                )
            }

            single<SignInRepository> {
                SignInRepositoryImpl(
                    firebaseAuth = get(),
                    firestore = get(),
                )
            }

            single<BankMovementsRepository> {
                BankMovementsRepositoryImpl(
                    firestore = get()
                )
            }

            single<BankMovementDetailsRepository> {
                BankMovementDetailsRepositoryImpl(
                    firestore = get()
                )
            }

        }

    }

}