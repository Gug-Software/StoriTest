package com.jkgug.example.storitest.usecase

import com.jkgug.example.storitest.repository.remote.signin.SignInRepository
import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface SignInUseCase {
    suspend operator fun invoke(
        userMail: String,
        userPassword: String
    ): Flow<NetworkResult<Any?>>
}

class SignInUseCaseImpl(
    private val signInRepository: SignInRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : SignInUseCase {
    override suspend operator fun invoke(
        userMail: String,
        userPassword: String
    ): Flow<NetworkResult<Any?>> {
        return withContext(ioDispatcher) {
            signInRepository.signInWithEmailAndPassword(userMail, userPassword)
        }
    }
}