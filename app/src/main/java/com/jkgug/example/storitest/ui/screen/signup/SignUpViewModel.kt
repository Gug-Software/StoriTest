package com.jkgug.example.storitest.ui.screen.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jkgug.example.storitest.data.UserData
import com.jkgug.example.storitest.data.repository.signup.SignUpRepository
import com.jkgug.example.storitest.utils.NetworkResult
import com.jkgug.example.storitest.utils.isValidEmail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signUpRepository: SignUpRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    var userName by mutableStateOf("")
        private set

    var userLastName by mutableStateOf("")
        private set

    var userMail by mutableStateOf("")
        private set

    var userPassword by mutableStateOf("")
        private set

    init {
        initScreen()
    }

    private fun initScreen() {
        _uiState.value = SignUpUiState(loading = false)
    }

    fun updateUserName(userName: String) {
        this.userName = userName
        checkEnabledSignInButton()
    }

    fun updateUserLastName(userLastName: String) {
        this.userLastName = userLastName
        checkEnabledSignInButton()
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

    fun snackBarMessageShown() {
        _uiState.update { it.copy(messageForUser = null) }
    }

    fun checkSignUp() {
        _uiState.update { currentState -> currentState.copy(loading = true) }
        val userData = getCurrentUserData()
        viewModelScope.launch {
            try {
                signUpRepository.createUserWithEmailAndPassword(userMail, userPassword)
                    .collect { createResult ->
                        when (createResult) {
                            is NetworkResult.Error -> updateMessageErrorForUser(createResult.message)
                            is NetworkResult.Success -> saveUserInFireStore(userData)
                        }
                    }
            } catch (e: Exception) {
                _uiState.update { it.copy(messageForUser = e.message) }
            }
        }
    }

    private fun getCurrentUserData() = UserData(userName = userName, userLastName = userLastName)

    private suspend fun saveUserInFireStore(userData: UserData) {
        signUpRepository.saveUserData(userData).collect { saveResult ->
            when (saveResult) {
                is NetworkResult.Error -> updateMessageErrorForUser(saveResult.message)
                is NetworkResult.Success -> _uiState.update { it.copy(navigateToSuccess = true) }
            }
        }
    }

    private fun updateMessageErrorForUser(message: String?) {
        message?.let { messageUser ->
            _uiState.update { it.copy(messageForUser = messageUser, loading = false) }
        }
    }

    private fun checkUserMail() {
        _uiState.update { currentState ->
            currentState.copy(isValidEmail = isValidEmail(userMail))
        }
    }

    private fun checkEnabledSignInButton() {
        val enabledButton = userMail.isNotEmpty()
                && userPassword.isNotEmpty()
                && userName.isNotEmpty()
                && userLastName.isNotEmpty()
        _uiState.update { currentState ->
            currentState.copy(enabledSignInButton = enabledButton)
        }
    }


}