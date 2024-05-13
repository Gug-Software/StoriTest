package com.jkgug.example.storitest.ui.screen.signin

data class SignInUiState(
    val loading: Boolean = false,
    val enabledSignInButton: Boolean = false,
    val navigateToHome: Boolean = false,
    val errorInCredentials: Boolean = false,
    val messageForUser: String? = null,
)