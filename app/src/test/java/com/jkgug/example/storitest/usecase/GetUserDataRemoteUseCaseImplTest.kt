package com.jkgug.example.storitest.usecase

import com.jkgug.example.storitest.domain.UserData
import com.jkgug.example.storitest.repository.remote.signin.SignInRepository
import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetUserDataRemoteUseCaseImplTest {

    @Mock
    private lateinit var signInRepository: SignInRepository

    private lateinit var useCase: GetUserDataRemoteUseCaseImpl

    private val firebaseUserId = "firebaseUserId"
    private val messageError = "User not found"
    private val userData = Mockito.mock(UserData::class.java)

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = GetUserDataRemoteUseCaseImpl(signInRepository)
    }

    @Test
    fun invoke_getUserDataFireStore_returnsSuccess() = runTest {
        // GIVEN
        Mockito.`when`(signInRepository.getUserDataFireStore(firebaseUserId)).thenReturn(
            flow { NetworkResult.Success(userData) }
        )

        // WHEN
        useCase(firebaseUserId)

        // THEN
        Mockito.verify(signInRepository).getUserDataFireStore(firebaseUserId)
    }

    @Test
    fun invoke_getUserDataFireStore_returnsError() = runTest {
        // GIVEN
        Mockito.`when`(signInRepository.getUserDataFireStore(firebaseUserId)).thenReturn(
            flow { NetworkResult.Error(message = messageError, data = null) }
        )

        // WHEN
        useCase(firebaseUserId)

        // THEN
        Mockito.verify(signInRepository).getUserDataFireStore(firebaseUserId)
    }

    @Test
    fun invoke_getUserDataFireStore_returnsSuccess_returnSuccessFlow() = runTest {
        // GIVEN
        Mockito.`when`(signInRepository.getUserDataFireStore(firebaseUserId)).thenReturn(
            flow { NetworkResult.Success(userData) }
        )

        // WHEN
        val flow = useCase(firebaseUserId)

        // THEN
        flow.collect { result ->
            assert(result is NetworkResult.Success)
            assert(result.data == userData)
        }
    }

    @Test
    fun invoke_getUserDataFireStore_returnsError_returnErrorFlow() = runTest {
        // GIVEN
        Mockito.`when`(signInRepository.getUserDataFireStore(firebaseUserId)).thenReturn(
            flow { NetworkResult.Error(message = messageError, data = null) }
        )

        // WHEN
        val flow = useCase(firebaseUserId)

        // THEN
        flow.collect { result ->
            assert(result is NetworkResult.Error)
            assert(result.message == messageError)
        }
    }
}