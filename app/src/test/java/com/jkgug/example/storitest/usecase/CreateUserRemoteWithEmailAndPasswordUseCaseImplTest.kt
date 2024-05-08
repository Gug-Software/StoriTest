package com.jkgug.example.storitest.usecase

import com.google.firebase.auth.FirebaseUser
import com.jkgug.example.storitest.repository.remote.signup.SignUpRepository
import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class CreateUserRemoteWithEmailAndPasswordUseCaseImplTest {


    @Mock
    private lateinit var signUpRepository: SignUpRepository

    private lateinit var useCase: CreateUserRemoteWithEmailAndPasswordUseCaseImpl

    private val email = "someemail"
    private val password = "somePassword"
    private val messageError = "User not found"
    private val firebaseUser = mock(FirebaseUser::class.java)

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = CreateUserRemoteWithEmailAndPasswordUseCaseImpl(signUpRepository)
    }

    @Test
    fun `invoke_whenSignInWithEmailAndPasswordSucceeds_returnsSuccess()`() = runTest {

        // GIVEN
        Mockito.`when`(signUpRepository.createUserWithEmailAndPassword(email, password)).thenReturn(
            flow { NetworkResult.Success(firebaseUser) }
        )

        // WHEN
        useCase(email, password)

        // THEN
        Mockito.verify(signUpRepository).createUserWithEmailAndPassword(email, password)
    }

    @Test
    fun `invoke_whenSignInWithEmailAndPasswordSucceeds_returnsError()`() = runTest {

        // GIVEN
        Mockito.`when`(signUpRepository.createUserWithEmailAndPassword(email, password)).thenReturn(
            flow { NetworkResult.Error(message = messageError, data = null) }
        )

        // WHEN
        useCase(email, password)

        // THEN
        Mockito.verify(signUpRepository).createUserWithEmailAndPassword(email, password)
    }

    @Test
    fun `flow emits successfully SUCCESS`(): Unit = runBlocking {

        // GIVEN
        Mockito.`when`(signUpRepository.createUserWithEmailAndPassword(email, password)).thenReturn(
            flow { NetworkResult.Success(firebaseUser) }
        )
        // WHEN
        val flow = useCase(email, password)

        // THEN
        flow.collect { result ->
            assert(result is NetworkResult.Success)
            assert(result.data == firebaseUser)
        }

    }

    @Test
    fun `flow emits successfully ERROR`(): Unit = runBlocking {

        // GIVEN
        Mockito.`when`(signUpRepository.createUserWithEmailAndPassword(email, password)).thenReturn(
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