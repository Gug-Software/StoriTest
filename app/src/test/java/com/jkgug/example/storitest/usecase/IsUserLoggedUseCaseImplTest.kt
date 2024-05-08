package com.jkgug.example.storitest.usecase

import com.jkgug.example.storitest.repository.local.sesion.SessionRepository
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class IsUserLoggedUseCaseImplTest {

    @Mock
    private lateinit var sessionRepository: SessionRepository

    private lateinit var useCase: IsUserLoggedUseCaseImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = IsUserLoggedUseCaseImpl(sessionRepository)
    }

    @Test
    fun `invoke_whenUserIsLogged_returnsTrue()`() {
        // Arrange
        `when`(sessionRepository.isUserLogged()).thenReturn(true)

        // Act
        val result = useCase()

        // Assert
        assertTrue(result)
    }

    @Test
    fun `invoke_whenUserIsLogged_returnsFalse()`() {
        // Arrange
        `when`(sessionRepository.isUserLogged()).thenReturn(false)

        // Act
        val result = useCase()

        // Assert
        assertFalse(result)
    }

}