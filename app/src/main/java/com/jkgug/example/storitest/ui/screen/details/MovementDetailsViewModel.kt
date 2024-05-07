package com.jkgug.example.storitest.ui.screen.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jkgug.example.storitest.data.BankMovement
import com.jkgug.example.storitest.data.repository.details.MovementDetailsRepository
import com.jkgug.example.storitest.ui.navigation.MovementDetails.MOVEMENT_ID_ARG
import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovementDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val movementDetailsRepository: MovementDetailsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovementDetailsUiState())
    val uiState: StateFlow<MovementDetailsUiState> = _uiState.asStateFlow()

    private val taskId: String? = savedStateHandle[MOVEMENT_ID_ARG]

    init {
        _uiState.update { it.copy(loadingContent = true) }
        getMovementsData()
    }

    fun getMovementsData() {
        taskId?.let { movementId ->
            updateStateAsInit()
            viewModelScope.launch {
                movementDetailsRepository.getMovementDetails(movementId)
                    .collect { networkResult ->
                        when (networkResult) {
                            is NetworkResult.Error -> updateMessageErrorForUser(networkResult.message)
                            is NetworkResult.Success -> {
                                val movementsList = networkResult.data as BankMovement
                                _uiState.update {
                                    it.copy(
                                        bankMovement = movementsList,
                                        loadingContent = false
                                    )
                                }
                            }
                        }
                    }
            }
        }
    }

    private fun updateStateAsInit() {
        _uiState.update {
            it.copy(
                bankMovement = BankMovement(),
                loadingContent = true,
                messageForUser = null
            )
        }
    }

    private fun updateMessageErrorForUser(message: String?) {
        message?.let { messageUser ->
            _uiState.update { it.copy(messageForUser = messageUser, loadingContent = false) }
        }
    }

}