package com.jkgug.example.storitest.usecase

import com.jkgug.example.storitest.repository.remote.signin.SignInRepository
import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface GetUserDataRemoteUseCase {
    suspend operator fun invoke(
        firebaseUserId: String
    ): Flow<NetworkResult<Any?>>
}

class GetUserDataRemoteUseCaseImpl(
    private val signInRepository: SignInRepository
) : GetUserDataRemoteUseCase {
    override suspend operator fun invoke(
        firebaseUserId: String
    ): Flow<NetworkResult<Any?>> {
        return signInRepository.getUserDataFireStore(firebaseUserId)
    }
}