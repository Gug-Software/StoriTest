package com.jkgug.example.storitest.ui.screen.signup

data class SignUpUiState(
    val loading: Boolean = false,
    val isValidEmail: Boolean = true,
    val enabledSignInButton: Boolean = false,
    val navigateToHome: Boolean = false,
    val errorInCredentials: Boolean = false,
    val messageForUser: String? = null,
)