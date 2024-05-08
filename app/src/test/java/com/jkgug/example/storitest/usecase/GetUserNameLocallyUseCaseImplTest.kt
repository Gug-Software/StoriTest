package com.jkgug.example.storitest.usecase

import com.jkgug.example.storitest.repository.local.userdata.UserDataRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetUserNameLocallyUseCaseImplTest {

    @Mock
    private lateinit var userDataRepository: UserDataRepository

    private lateinit var useCase: GetUserNameLocallyUseCaseImpl

    private val name = "name"

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = GetUserNameLocallyUseCaseImpl(userDataRepository)
    }

    @Test
    fun `invoke_whenUserIsLogged_returnsTrue()`() {
        runTest {
            // Arrange
            Mockito.`when`(userDataRepository.getLocallyUserName()).thenReturn(name)

            // Act
            val result = useCase()

            // Assert
            assertTrue(result.isNotEmpty())
        }
    }

    @Test
    fun `invoke_whenUserIsLogged_returnsFalse()`() {
        runTest {
            // Arrange
            Mockito.`when`(userDataRepository.getLocallyUserName()).thenReturn("")

            // Act
            val result = useCase()

            // Assert
            assertTrue(result.isEmpty())
        }
    }

}