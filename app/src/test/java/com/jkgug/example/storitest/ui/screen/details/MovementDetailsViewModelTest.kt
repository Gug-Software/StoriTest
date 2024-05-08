package com.jkgug.example.storitest.ui.screen.details

import androidx.lifecycle.SavedStateHandle
import com.jkgug.example.storitest.MainCoroutineRule
import com.jkgug.example.storitest.usecase.GetBankMovementDetailsRemoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MovementDetailsViewModelTest {


    @Mock
    private lateinit var savedStateHandle: SavedStateHandle

    @Mock
    private lateinit var getBankMovementDetailsRemoteUseCase: GetBankMovementDetailsRemoteUseCase

    private lateinit var viewModel: MovementDetailsViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = MovementDetailsViewModel(savedStateHandle, getBankMovementDetailsRemoteUseCase)
    }

    @Test
    fun getMovementsData() = runTest {
        Dispatchers.setMain(Dispatchers.Unconfined) // Set the Main dispatcher for tests
        assert(viewModel.uiState.value.loadingContent)
        viewModel.getMovementsData()
        // Execute pending coroutines actions
        advanceUntilIdle()
        assert(viewModel.uiState.value.loadingContent.not())
    }
}