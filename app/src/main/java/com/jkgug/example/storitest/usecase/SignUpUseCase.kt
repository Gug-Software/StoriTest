package com.jkgug.example.storitest.usecase

import com.jkgug.example.storitest.domain.UserData
import com.jkgug.example.storitest.repository.remote.signup.SignUpRepository
import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface SignUpUseCase {
    suspend operator fun invoke(
        userMail: String,
        userPassword: String,
        userData: UserData
    ): Flow<NetworkResult<Any?>>
}

class SignUpUseCaseImpl(
    private val signUpRepository: SignUpRepository
) : SignUpUseCase {

    override suspend fun invoke(
        userMail: String,
        userPassword: String,
        userData: UserData
    ): Flow<NetworkResult<Any?>> {
        return signUpRepository.signUpUserAndSaveUserData(userMail, userPassword, userData)
    }

}