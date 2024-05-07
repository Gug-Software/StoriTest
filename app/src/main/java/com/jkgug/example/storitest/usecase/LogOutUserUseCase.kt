package com.jkgug.example.storitest.usecase

import com.jkgug.example.storitest.repository.local.sesion.SessionRepository

interface LogOutUserUseCase {
    suspend operator fun invoke()
}

class LogOutUserUseCaseImpl(
    private val sessionRepository: SessionRepository
) : LogOutUserUseCase {
    override suspend fun invoke() {
        sessionRepository.saveIsLogged(false)
    }
}