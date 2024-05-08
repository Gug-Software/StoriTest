package com.jkgug.example.storitest.usecase

import com.jkgug.example.storitest.repository.local.sesion.SessionRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.verify

class LogOutUserUseCaseImplTest {

    @Mock
    private lateinit var sessionRepository: SessionRepository

    private lateinit var useCase: LogOutUserUseCaseImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = LogOutUserUseCaseImpl(sessionRepository)
    }

    @Test
    fun invokeLogOutUser_usesRepositorySaveIsLogged() {
        // Arrange
        doNothing().`when`(sessionRepository).saveIsLogged(false)

        // Act
        useCase()

        // Assert
        verify(sessionRepository).saveIsLogged(false)
    }

}