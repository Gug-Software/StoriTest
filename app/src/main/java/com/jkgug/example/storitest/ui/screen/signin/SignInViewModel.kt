package com.jkgug.example.storitest.ui.screen.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jkgug.example.storitest.utils.isValidEmail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState: StateFlow<SignInUiState> = _uiState.asStateFlow()

    var userMail by mutableStateOf("")
        private set

    var userPassword by mutableStateOf("")
        private set

    init {
        initScreen()
    }

    private fun initScreen() {
        _uiState.value = SignInUiState(
            loading = false
        )
    }

    fun updateUserMail(userMail: String) {
        this.userMail = userMail
        checkUserMail()
        checkEnabledSignInButton()
    }

    fun updateUserPassword(userPassword: String) {
        this.userPassword = userPassword
        checkEnabledSignInButton()
    }

    private fun checkUserMail() {
        if (isValidEmail(userMail)) {
            _uiState.update { currentState ->
                currentState.copy(isValidEmail = true)
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(isValidEmail = false)
            }
        }
    }

    private fun checkEnabledSignInButton() {
        val enabledButton = userMail.isNotEmpty() && userPassword.isNotEmpty()
        _uiState.update { currentState ->
            currentState.copy(enabledSignInButton = enabledButton)
        }
    }

    fun checkSignIn() {
        if (userMail.isNotEmpty() && userPassword == "admin") {
            _uiState.update { currentState ->
                currentState.copy(navigateToHome = true)
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(errorInCredentials = true, navigateToHome = false)
            }
        }
    }


}