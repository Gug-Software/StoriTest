package com.jkgug.example.storitest.usecase

import com.jkgug.example.storitest.repository.remote.signup.SignUpRepository
import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface CreateUserRemoteWithEmailAndPasswordUseCase {
    suspend operator fun invoke(
        userMail: String,
        userPassword: String
    ): Flow<NetworkResult<Any?>>
}

class CreateUserRemoteWithEmailAndPasswordUseCaseImpl(
    private val signUpRepository: SignUpRepository
) : CreateUserRemoteWithEmailAndPasswordUseCase {
    override suspend fun invoke(
        userMail: String,
        userPassword: String
    ): Flow<NetworkResult<Any?>> {
        return signUpRepository.createUserWithEmailAndPassword(userMail, userPassword)
    }


}