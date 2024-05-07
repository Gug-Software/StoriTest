package com.jkgug.example.storitest.ui.screen.home

import com.jkgug.example.storitest.data.BankMovement

data class HomeUiState(
    val loadingContent: Boolean = false,
    val userName: String = "",
    val messageForUser: String? = null,
    val movements: List<BankMovement> = listOf(),
    val navigateToSignIn: Boolean = false
)