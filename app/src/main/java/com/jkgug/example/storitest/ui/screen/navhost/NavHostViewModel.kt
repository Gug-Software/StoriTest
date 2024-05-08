package com.jkgug.example.storitest.ui.screen.navhost

import androidx.lifecycle.ViewModel
import com.jkgug.example.storitest.usecase.IsUserLoggedUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class NavHostViewModel(
    private val isUserLoggedUseCase: IsUserLoggedUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(NavHostUiState())
    val uiState: StateFlow<NavHostUiState> = _uiState.asStateFlow()

    init {
        initScreen()
    }

    private fun initScreen() {
        _uiState.value = NavHostUiState(isUserLogged = isUserLoggedUseCase())
    }

}