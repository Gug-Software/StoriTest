package com.jkgug.example.storitest.usecase

import com.jkgug.example.storitest.repository.local.sesion.SessionRepository
import com.jkgug.example.storitest.repository.local.userdata.UserDataRepository
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class SaveUserDataLocallyUseCaseImplTest {

    @Mock
    private lateinit var userDataRepository: UserDataRepository

    @Mock
    private lateinit var sessionRepository: SessionRepository


    private lateinit var useCase: SaveUserDataLocallyUseCaseImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = SaveUserDataLocallyUseCaseImpl(userDataRepository, sessionRepository)
    }

    @Test
    fun `invoke_savesUserDataAndUpdatesLoginStatus()`() = runTest {
        // Arrange
        val userName = "test_user"

        // Act
        useCase(userName)

        // Assert
        verify(userDataRepository).saveLocallyUserData(userName)
        verify(sessionRepository).saveIsLogged(true)
    }


}