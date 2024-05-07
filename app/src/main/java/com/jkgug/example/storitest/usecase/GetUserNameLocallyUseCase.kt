package com.jkgug.example.storitest.usecase

import com.jkgug.example.storitest.repository.local.userdata.UserDataRepository

interface GetUserNameLocallyUseCase {
    suspend operator fun invoke(): String
}

class GetUserNameLocallyUseCaseImpl(
    private val userDataRepository: UserDataRepository,
) : GetUserNameLocallyUseCase {
    override suspend fun invoke() = userDataRepository.getLocallyUserName()
}