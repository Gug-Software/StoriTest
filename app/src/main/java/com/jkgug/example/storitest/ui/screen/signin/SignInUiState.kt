package com.jkgug.example.storitest.ui.screen.signin

data class SignInUiState(
    val loading: Boolean = false,
    val isValidEmail: Boolean = true,
    val enabledSignInButton: Boolean = false,
    val navigateToHome: Boolean = false,
    val errorInCredentials: Boolean = false,
)