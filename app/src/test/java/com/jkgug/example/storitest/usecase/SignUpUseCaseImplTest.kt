package com.jkgug.example.storitest.usecase

import com.google.firebase.auth.FirebaseUser
import com.jkgug.example.storitest.domain.UserData
import com.jkgug.example.storitest.repository.remote.signup.SignUpRepository
import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class SignUpUseCaseImplTest {


    @Mock
    private lateinit var signUpRepository: SignUpRepository

    private lateinit var useCase: SignUpUseCaseImpl

    private val email = "someemail"
    private val password = "somePassword"
    private val messageError = "User not found"
    private val userData = UserData("name", "lastname")
    private val firebaseUser = mock(FirebaseUser::class.java)

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = SignUpUseCaseImpl(signUpRepository)
    }

    @Test
    fun `invoke_whenSignUpUserAndSaveUserData returnsSuccess useRepository`() = runTest {
        // GIVEN
        Mockito.`when`(signUpRepository.signUpUserAndSaveUserData(email, password, userData))
            .thenReturn(
                flow { NetworkResult.Success(true) }
            )

        // WHEN
        useCase(email, password, userData)

        // THEN
        Mockito.verify(signUpRepository).signUpUserAndSaveUserData(email, password, userData)
    }

    @Test
    fun `invoke_whenSignUpUserAndSaveUserData returnsError and useRepository`() = runTest {
        // GIVEN
        Mockito.`when`(signUpRepository.signUpUserAndSaveUserData(email, password, userData))
            .thenReturn(
                flow { NetworkResult.Error(message = messageError, data = null) }
            )

        // WHEN
        useCase(email, password, userData)

        // THEN
        Mockito.verify(signUpRepository).signUpUserAndSaveUserData(email, password, userData)
    }

    @Test
    fun `invoke_whenSignUpUserAndSaveUserData_flow is success`() = runTest {
        // GIVEN
        Mockito.`when`(signUpRepository.signUpUserAndSaveUserData(email, password, userData))
            .thenReturn(
                flow { NetworkResult.Success(true) }
            )
        // WHEN
        val flow = useCase(email, password, userData)

        // THEN
        flow.collect { result ->
            assert(result is NetworkResult.Success)
            assert(result.data == firebaseUser)
        }
    }

    @Test
    fun `invoke_whenSignUpUserAndSaveUserData_flow is error`(): Unit = runTest {
        // GIVEN
        Mockito.`when`(signUpRepository.signUpUserAndSaveUserData(email, password, userData))
            .thenReturn(
                flow { NetworkResult.Error(message = messageError, data = null) }
            )
        // WHEN
        val flow = useCase(email, password, userData)

        // THEN
        flow.collect { result ->
            assert(result is NetworkResult.Error)
            assert(result.message == messageError)
        }
    }

}