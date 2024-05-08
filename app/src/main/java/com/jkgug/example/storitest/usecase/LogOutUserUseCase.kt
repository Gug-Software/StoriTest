package com.jkgug.example.storitest.usecase

import com.jkgug.example.storitest.repository.local.sesion.SessionRepository

interface LogOutUserUseCase {
    operator fun invoke()
}

class LogOutUserUseCaseImpl(
    private val sessionRepository: SessionRepository
) : LogOutUserUseCase {
    override fun invoke() {
        sessionRepository.saveIsLogged(false)
    }
}