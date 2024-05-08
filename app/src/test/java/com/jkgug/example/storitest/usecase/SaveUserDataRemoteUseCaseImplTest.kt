package com.jkgug.example.storitest.usecase

import com.jkgug.example.storitest.domain.UserData
import com.jkgug.example.storitest.repository.remote.signup.SignUpRepository
import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class SaveUserDataRemoteUseCaseImplTest {

    @Mock
    private lateinit var signUpRepository: SignUpRepository

    private lateinit var useCase: SaveUserDataRemoteUseCaseImpl

    private val messageError = "User not found"

    private val userData = UserData(
        userName = "Leta Bird", userLastName = "Tommie Doyle"
    )
    private val userDataId = "uid"

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = SaveUserDataRemoteUseCaseImpl(signUpRepository)
    }

    @Test
    fun invoke_whenSignInWithEmailAndPasswordSucceeds_usesSignUpRepository() = runTest {

        // GIVEN
        `when`(signUpRepository.saveUserData(userData)).thenReturn(
            flow { NetworkResult.Success(userDataId) }
        )

        // WHEN
        useCase(userData)

        // THEN
        verify(signUpRepository).saveUserData(userData)
    }

    @Test
    fun invoke_whenSignInWithEmailAndPassword_usesSignUpRepositoryAndReturnsError() = runTest {
        // GIVEN
        `when`(signUpRepository.saveUserData(userData)).thenReturn(
            flow { NetworkResult.Error(message = messageError, data = null) }
        )

        // WHEN
        useCase(userData)

        // THEN
        verify(signUpRepository).saveUserData(userData)
    }

    @Test
    fun invoke_whenSignInWithEmailAndPasswordSucceeds_returnSuccessFlow() = runTest {
        // GIVEN
        `when`(signUpRepository.saveUserData(userData)).thenReturn(
            flow { NetworkResult.Success(userDataId) }
        )

        // WHEN
        val flow = useCase(userData)

        // THEN
        flow.collect { result ->
            assert(result is NetworkResult.Success)
            assert(result.data == userDataId)
        }
    }

    @Test
    fun invoke_whenSignInWithEmailAndPasswordError_returnErrorFlow() = runTest {
        // GIVEN
        `when`(signUpRepository.saveUserData(userData)).thenReturn(
            flow { NetworkResult.Error(message = messageError, data = null) }
        )

        // WHEN
        val flow = useCase(userData)

        // THEN
        flow.collect { result ->
            assert(result is NetworkResult.Error)
            assert(result.message == messageError)
        }
    }


}