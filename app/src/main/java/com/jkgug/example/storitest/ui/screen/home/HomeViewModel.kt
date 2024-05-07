package com.jkgug.example.storitest.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jkgug.example.storitest.domain.BankMovement
import com.jkgug.example.storitest.usecase.GetBankMovementsRemoteUseCase
import com.jkgug.example.storitest.usecase.GetUserNameLocallyUseCase
import com.jkgug.example.storitest.usecase.LogOutUserUseCase
import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getUserNameLocallyUseCase: GetUserNameLocallyUseCase,
    private val getBankMovementsRemoteUseCase: GetBankMovementsRemoteUseCase,
    private val logOutUserUseCase: LogOutUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        _uiState.update { it.copy(loadingContent = true) }
        getUserLocalData()
        getMovementsData()
    }

    fun getMovementsData() {

        updateStateAsInit()

        viewModelScope.launch {
            getBankMovementsRemoteUseCase.invoke().collect { networkResult ->
                when (networkResult) {
                    is NetworkResult.Error -> updateMessageErrorForUser(networkResult.message)
                    is NetworkResult.Success -> setMovementsListInUiState(networkResult)
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch { logOutUserUseCase.invoke() }
        _uiState.update { it.copy(navigateToSignIn = true) }
    }

    private fun getUserLocalData() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(userName = getUserNameLocallyUseCase.invoke())
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun setMovementsListInUiState(networkResult: NetworkResult<Any?>) {
        val movementsList = networkResult.data as MutableList<BankMovement>
        _uiState.update {
            it.copy(
                movements = movementsList.toList(),
                loadingContent = false
            )
        }
    }

    private fun updateStateAsInit() {
        _uiState.update {
            it.copy(
                movements = listOf(),
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