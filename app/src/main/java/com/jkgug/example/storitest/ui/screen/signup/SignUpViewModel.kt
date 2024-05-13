package com.jkgug.example.storitest.ui.screen.signup

import android.util.Patterns
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jkgug.example.storitest.domain.UserData
import com.jkgug.example.storitest.usecase.SignUpUseCase
import com.jkgug.example.storitest.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase
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

    val emailHasErrors by derivedStateOf {
        if (userMail.isNotEmpty()) {
            // Email is considered erroneous until it completely matches EMAIL_ADDRESS.
            Patterns.EMAIL_ADDRESS.matcher(userMail).matches()
        } else {
            false
        }
    }

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
        checkEnabledSignInButton()
    }

    fun updateUserPassword(userPassword: String) {
        this.userPassword = userPassword
        checkEnabledSignInButton()
    }

    fun snackBarMessageShown() {
        _uiState.update { it.copy(messageForUser = null) }
    }

    fun signUp() {
        _uiState.update { currentState -> currentState.copy(loading = true) }
        viewModelScope.launch {
            signUpUseCase(
                userMail,
                userPassword,
                getCurrentUserData()
            ).catch { updateMessageErrorForUser(it.message) }
                .collect { createResult ->
                    when (createResult) {
                        is NetworkResult.Error -> updateMessageErrorForUser(createResult.message)
                        is NetworkResult.Success -> navigateToSuccess()
                    }
                }
        }
    }

    private fun navigateToSuccess() = _uiState.update { it.copy(navigateToSuccess = true) }

    private fun getCurrentUserData() = UserData(userName = userName, userLastName = userLastName)

    private fun updateMessageErrorForUser(message: String?) = message?.let { messageUser ->
        _uiState.update { it.copy(messageForUser = messageUser, loading = false) }
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