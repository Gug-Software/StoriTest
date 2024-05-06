package com.jkgug.example.storitest.ui.screen.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jkgug.example.storitest.data.UserData
import com.jkgug.example.storitest.data.repository.signin.SignInRepository
import com.jkgug.example.storitest.utils.NetworkResult
import com.jkgug.example.storitest.utils.isValidEmail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel(
    private val signInRepository: SignInRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState: StateFlow<SignInUiState> = _uiState.asStateFlow()

    var userMail by mutableStateOf("test1@gmail.com")
        private set

    var userPassword by mutableStateOf("12345")
        private set

    init {
        initScreen()
    }

    private fun initScreen() {
        _uiState.value = SignInUiState(loading = false)
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

    fun checkSignIn() {
        _uiState.update { currentState -> currentState.copy(loading = true) }
        viewModelScope.launch {
            try {
                signInRepository.signInWithEmailAndPassword(userMail, userPassword)
                    .collect { networkResult ->
                        when (networkResult) {
                            is NetworkResult.Success -> getUserDataAndSaveLocally(networkResult.data as String)
                            is NetworkResult.Error -> updateMessageErrorForUser(networkResult.message)
                        }
                    }
            } catch (e: Exception) {
                _uiState.update { it.copy(messageForUser = e.message) }
            }
        }
    }

    fun snackBarMessageShown() {
        _uiState.update { it.copy(messageForUser = null) }
    }

    private suspend fun getUserDataAndSaveLocally(firebaseUserId: String) {
        signInRepository.getUserDataFireStore(firebaseUserId)
            .collect { networkResult ->
                when (networkResult) {
                    is NetworkResult.Success -> saveLocallyUserData(networkResult.data as UserData)
                    is NetworkResult.Error -> updateMessageErrorForUser(networkResult.message)
                }
            }
    }

    private suspend fun saveLocallyUserData(userData: UserData) {
        signInRepository.saveLocallyUserData(userData)
        navigateToHome()
    }

    private fun navigateToHome() {
        _uiState.update { it.copy(navigateToHome = true) }
    }

    private fun checkEnabledSignInButton() {
        val enabledButton = userMail.isNotEmpty() && userPassword.isNotEmpty()
        _uiState.update { currentState ->
            currentState.copy(enabledSignInButton = enabledButton)
        }
    }

    private fun checkUserMail() {
        _uiState.update { currentState -> currentState.copy(isValidEmail = isValidEmail(userMail)) }
    }

    private fun updateMessageErrorForUser(message: String?) {
        message?.let { message ->
            _uiState.update { it.copy(messageForUser = message, loading = false) }
        }
    }

}