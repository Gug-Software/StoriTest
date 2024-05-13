package com.jkgug.example.storitest.usecase

import com.jkgug.example.storitest.repository.remote.signin.SignInRepository
import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class SignInUseCaseImplTest {

    @Mock
    private lateinit var signInRepository: SignInRepository

    private lateinit var useCase: SignInUseCaseImpl

    private val email = "someemail"
    private val password = "somePassword"
    private val messageError = "User not found"
    private val firebaseUserId = "firebaseUserId"

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = SignInUseCaseImpl(signInRepository)
    }

    @Test
    fun invoke_whenSignInWithEmailAndPasswordSucceeds_usesRepository() = runTest {
        // GIVEN
        `when`(signInRepository.signInWithEmailAndPassword(email, password)).thenReturn(
            flow { NetworkResult.Success(firebaseUserId) }
        )

        // WHEN
        useCase(email, password)

        // THEN
        verify(signInRepository).signInWithEmailAndPassword(email, password)
    }

    @Test
    fun invoke_whenSignInWithEmailAndPasswordError_usesRepository() = runTest {
        // GIVEN
        `when`(signInRepository.signInWithEmailAndPassword(email, password)).thenReturn(
            flow { NetworkResult.Error(message = messageError, data = null) }
        )

        // WHEN
        useCase(email, password)

        // THEN
        verify(signInRepository).signInWithEmailAndPassword(email, password)
    }

    @Test
    fun invoke_whenSignInWithEmailAndPasswordSucceeds_retrurnsSuccessFlow() = runTest {
        // GIVEN
        `when`(signInRepository.signInWithEmailAndPassword(email, password)).thenReturn(
            flow { NetworkResult.Success(firebaseUserId) }
        )

        // WHEN
        val flow = useCase(email, password)

        // THEN
        flow.collect { result ->
            assert(result is NetworkResult.Success)
            assert(result.data == firebaseUserId)
        }
    }

    @Test
    fun invoke_whenSignInWithEmailAndPasswordError_returnsErrorFlow() = runTest {
        // GIVEN
        `when`(signInRepository.signInWithEmailAndPassword(email, password)).thenReturn(
            flow { NetworkResult.Error(message = messageError, data = null) }
        )

        // WHEN
        val flow = useCase(email, password)

        // THEN
        flow.collect { result ->
            assert(result is NetworkResult.Error)
            assert(result.message == messageError)
        }
    }
}