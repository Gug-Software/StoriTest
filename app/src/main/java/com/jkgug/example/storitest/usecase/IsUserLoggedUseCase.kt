package com.jkgug.example.storitest.usecase

import com.jkgug.example.storitest.repository.local.sesion.SessionRepository

interface IsUserLoggedUseCase {
    operator fun invoke(): Boolean
}

class IsUserLoggedUseCaseImpl(
    private val sessionRepository: SessionRepository
) : IsUserLoggedUseCase {
    override fun invoke(): Boolean {
        return sessionRepository.isUserLogged()
    }
}