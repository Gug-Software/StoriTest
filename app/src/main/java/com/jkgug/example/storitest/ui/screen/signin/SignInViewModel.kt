package com.jkgug.example.storitest.ui.screen.signin

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jkgug.example.storitest.domain.UserData
import com.jkgug.example.storitest.usecase.SaveUserDataLocallyUseCase
import com.jkgug.example.storitest.usecase.SignInUseCase
import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel(
    private val signInRemoteWithEmailAndPasswordUseCase: SignInUseCase,
    private val saveUserDataLocallyUseCase: SaveUserDataLocallyUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState: StateFlow<SignInUiState> = _uiState.asStateFlow()

    var userMail by mutableStateOf("")
        private set

    var userPassword by mutableStateOf("")
        private set

    val emailHasErrors by derivedStateOf {
        if (userMail.isNotEmpty()) {
            // Email is considered erroneous until it completely matches EMAIL_ADDRESS.
            android.util.Patterns.EMAIL_ADDRESS.matcher(userMail).matches()
        } else {
            false
        }
    }

    init {
        initScreen()
    }

    private fun initScreen() {
        _uiState.value = SignInUiState(loading = false)
    }

    fun updateUserMail(userMail: String) {
        this.userMail = userMail
        checkEnabledSignInButton()
    }

    fun updateUserPassword(userPassword: String) {
        this.userPassword = userPassword
        checkEnabledSignInButton()
    }

    fun signIn() {
        _uiState.update { currentState -> currentState.copy(loading = true) }
        viewModelScope.launch {
            signInRemoteWithEmailAndPasswordUseCase(userMail, userPassword)
                .catch { updateMessageErrorForUser(it.message) }
                .collect { networkResult ->
                    when (networkResult) {
                        is NetworkResult.Success -> saveLocallyUserData(networkResult.data as UserData)
                        is NetworkResult.Error -> updateMessageErrorForUser(networkResult.message)
                    }
                }
        }
    }

    fun snackBarMessageShown() = _uiState.update { it.copy(messageForUser = null) }

    private suspend fun saveLocallyUserData(userData: UserData) {
        saveUserDataLocallyUseCase(userData.userName)
        navigateToHome()
    }

    private fun navigateToHome() = _uiState.update { it.copy(navigateToHome = true) }

    private fun checkEnabledSignInButton() {
        val enabledButton = userMail.isNotEmpty() && userPassword.isNotEmpty()
        _uiState.update { currentState ->
            currentState.copy(enabledSignInButton = enabledButton)
        }
    }

    private fun updateMessageErrorForUser(message: String?) = message?.let { messageUser ->
        _uiState.update { it.copy(messageForUser = messageUser, loading = false) }
    }

}