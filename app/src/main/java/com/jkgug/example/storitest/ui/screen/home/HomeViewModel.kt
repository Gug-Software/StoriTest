package com.jkgug.example.storitest.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jkgug.example.storitest.data.BankMovement
import com.jkgug.example.storitest.data.repository.home.HomeRepository
import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        getUserLocalData()
        getMovementsData()
    }

    private fun getUserLocalData() {
        viewModelScope.launch {
            val userName = homeRepository.getUserLocalData()
            _uiState.update { it.copy(userName = userName) }
        }
    }

    private fun getMovementsData() {
        viewModelScope.launch {
            homeRepository.getMovementsData().collect { networkResult ->
                when (networkResult) {
                    is NetworkResult.Error -> updateMessageErrorForUser(networkResult.message)
                    is NetworkResult.Success -> {
                        val movementsList = networkResult.data as MutableList<BankMovement>
                        _uiState.update {
                            it.copy(
                                movements = movementsList.toList(),
                                loadingContent = false
                            )
                        }
                    }
                }
            }
        }
    }

    private fun updateMessageErrorForUser(message: String?) {
        message?.let { message ->
            _uiState.update { it.copy(messageForUser = message, loadingContent = false) }
        }
    }

}