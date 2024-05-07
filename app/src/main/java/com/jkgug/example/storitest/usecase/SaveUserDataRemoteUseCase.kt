package com.jkgug.example.storitest.usecase

import com.jkgug.example.storitest.repository.remote.signup.SignUpRepository
import com.jkgug.example.storitest.domain.UserData
import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface SaveUserDataRemoteUseCase {
    suspend operator fun invoke(
        userData: UserData
    ): Flow<NetworkResult<Any>>
}

class SaveUserDataRemoteUseCaseImpl(
    private val signUpRepository: SignUpRepository
) : SaveUserDataRemoteUseCase {
    override suspend operator fun invoke(
        userData: UserData
    ): Flow<NetworkResult<Any>> {
        return signUpRepository.saveUserData(userData)
    }
}