package com.jkgug.example.storitest.usecase

import com.jkgug.example.storitest.repository.remote.movement.BankMovementsRepository
import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


class GetBankMovementsRemoteUseCaseImplTest {

    @Mock
    private lateinit var bankMovementsRepository: BankMovementsRepository

    private lateinit var useCase: GetBankMovementsRemoteUseCaseImpl

    private val messageError = "message error"
    private val bankMovementList = Mockito.mock(List::class.java)

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = GetBankMovementsRemoteUseCaseImpl(bankMovementsRepository)
    }

    @Test
    fun `invoke_whenSignInWithEmailAndPasswordSucceeds_returnsSuccess()`() = runTest {

        // GIVEN
        `when`(bankMovementsRepository.getMovementsData()).thenReturn(
            flow { NetworkResult.Success(bankMovementList) }
        )

        // WHEN
        useCase()

        // THEN
        Mockito.verify(bankMovementsRepository).getMovementsData()
    }

    @Test
    fun `invoke_whenSignInWithEmailAndPasswordSucceeds_returnsError()`() = runTest {

        // GIVEN
        `when`(bankMovementsRepository.getMovementsData()).thenReturn(
            flow { NetworkResult.Error(message = messageError, data = null) }
        )

        // WHEN
        useCase()

        // THEN
        Mockito.verify(bankMovementsRepository).getMovementsData()
    }

    @Test
    fun `flow emits successfully SUCCESS`(): Unit = runBlocking {

        // GIVEN
        `when`(bankMovementsRepository.getMovementsData()).thenReturn(
            flow { NetworkResult.Success(bankMovementList) }
        )
        // WHEN
        val flow = useCase()

        // THEN
        flow.collect { result ->
            assert(result is NetworkResult.Success)
            assert(result.data == bankMovementList)
        }
    }

    @Test
    fun `flow emits successfully SUCCESS empty list`(): Unit = runBlocking {

        // GIVEN
        `when`(bankMovementsRepository.getMovementsData()).thenReturn(
            flow { NetworkResult.Success(bankMovementList) }
        )

        // WHEN
        val flow = useCase()

        // THEN
        flow.collect { result ->
            assert(result is NetworkResult.Success)
            assert(result.data == bankMovementList)
        }
    }

    @Test
    fun `flow emits successfully ERROR`(): Unit = runBlocking {

        // GIVEN
        `when`(bankMovementsRepository.getMovementsData()).thenReturn(
            flow { NetworkResult.Error(message = messageError, data = null) }
        )
        // WHEN
        val flow = useCase()

        // THEN
        flow.collect { result ->
            assert(result is NetworkResult.Error)
            assert(result.message == messageError)
        }
    }

}