package com.jkgug.example.storitest.usecase

import com.jkgug.example.storitest.repository.remote.signin.SignInRepository
import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface SignInRemoteWithEmailAndPasswordUseCase {
    suspend operator fun invoke(
        userMail: String,
        userPassword: String
    ): Flow<NetworkResult<Any?>>
}

class SignInRemoteWithEmailAndPasswordUseCaseImpl(
    private val signInRepository: SignInRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : SignInRemoteWithEmailAndPasswordUseCase {
    override suspend operator fun invoke(
        userMail: String,
        userPassword: String
    ): Flow<NetworkResult<Any?>> {
        return withContext(defaultDispatcher) {
            signInRepository.signInWithEmailAndPassword(userMail, userPassword)
        }
    }
}