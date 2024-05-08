package com.jkgug.example.storitest.usecase

import com.jkgug.example.storitest.domain.BankMovement
import com.jkgug.example.storitest.repository.remote.movement.BankMovementDetailsRepository
import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetBankMovementDetailsRemoteUseCaseImplTest {

    @Mock
    private lateinit var bankMovementDetailsRepository: BankMovementDetailsRepository

    private lateinit var useCase: GetBankMovementDetailsRemoteUseCaseImpl

    private val movementId = "movementId"
    private val messageError = "User not found"
    private val bankMovement = Mockito.mock(BankMovement::class.java)

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = GetBankMovementDetailsRemoteUseCaseImpl(bankMovementDetailsRepository)
    }

    @Test
    fun `invokeGetMovementDetails_returnsSuccess`() = runTest {
        // GIVEN
        Mockito.`when`(bankMovementDetailsRepository.getMovementDetails(movementId)).thenReturn(
            flow { NetworkResult.Success(bankMovement) }
        )
        // WHEN
        useCase(movementId)

        // THEN
        Mockito.verify(bankMovementDetailsRepository).getMovementDetails(movementId)
    }

    @Test
    fun `invokeGetMovementDetails_returnsError`() = runTest {
        // GIVEN
        Mockito.`when`(bankMovementDetailsRepository.getMovementDetails(movementId)).thenReturn(
            flow { NetworkResult.Error(message = "User not found", data = null) }
        )
        // WHEN
        useCase(movementId)

        // THEN
        Mockito.verify(bankMovementDetailsRepository).getMovementDetails(movementId)
    }

    @Test
    fun `flow emits successfully SUCCESS`(): Unit = runBlocking {
        // GIVEN
        Mockito.`when`(bankMovementDetailsRepository.getMovementDetails(movementId)).thenReturn(
            flow { NetworkResult.Success(bankMovement) }
        )
        // WHEN
        val flow = useCase(movementId)

        // THEN
        flow.collect { result ->
            assert(result is NetworkResult.Success)
            assert(result.data == bankMovement)
        }
    }

    @Test
    fun `flow emits successfully ERROR`(): Unit = runBlocking {
        // GIVEN
        Mockito.`when`(bankMovementDetailsRepository.getMovementDetails(movementId)).thenReturn(
            flow { NetworkResult.Error(message = messageError, data = null) }
        )
        // WHEN
        val flow = useCase(movementId)

        // THEN
        flow.collect { result ->
            assert(result is NetworkResult.Error)
            assert(result.message == messageError)
        }

    }


}