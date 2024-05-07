package com.jkgug.example.storitest.usecase

import com.jkgug.example.storitest.repository.local.sesion.SessionRepository
import com.jkgug.example.storitest.repository.local.userdata.UserDataRepository

interface SaveUserDataLocallyUseCase {
    suspend operator fun invoke(
        userName: String
    )
}

class SaveUserDataLocallyUseCaseImpl(
    private val userDataRepository: UserDataRepository,
    private val sessionRepository: SessionRepository
) : SaveUserDataLocallyUseCase {
    override suspend fun invoke(
        userName: String
    ) {
        userDataRepository.saveLocallyUserData(userName)
        sessionRepository.saveIsLogged(true)
    }
}