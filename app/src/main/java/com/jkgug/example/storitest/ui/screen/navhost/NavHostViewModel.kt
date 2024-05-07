package com.jkgug.example.storitest.ui.screen.navhost

import androidx.lifecycle.ViewModel
import com.jkgug.example.storitest.data.repository.logged.LoggedRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class NavHostViewModel(
    private val loggedRepository: LoggedRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(NavHostUiState())
    val uiState: StateFlow<NavHostUiState> = _uiState.asStateFlow()

    init {
        initScreen()
    }

    private fun initScreen() {
        _uiState.value = NavHostUiState(isUserLogged = loggedRepository.isUserLogged())
    }

}