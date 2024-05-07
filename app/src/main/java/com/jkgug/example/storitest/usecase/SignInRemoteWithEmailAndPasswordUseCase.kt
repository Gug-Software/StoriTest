package com.jkgug.example.storitest.usecase

import com.jkgug.example.storitest.repository.remote.signin.SignInRepository
import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface SignInRemoteWithEmailAndPasswordUseCase {
    suspend operator fun invoke(
        userMail: String,
        userPassword: String
    ): Flow<NetworkResult<Any?>>
}

class SignInRemoteWithEmailAndPasswordUseCaseImpl(
    private val signInRepository: SignInRepository
) : SignInRemoteWithEmailAndPasswordUseCase {
    override suspend operator fun invoke(
        userMail: String,
        userPassword: String
    ): Flow<NetworkResult<Any?>> {
        return signInRepository.signInWithEmailAndPassword(userMail, userPassword)
    }
}