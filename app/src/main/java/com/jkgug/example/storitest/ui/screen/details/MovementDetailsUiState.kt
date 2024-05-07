package com.jkgug.example.storitest.ui.screen.details

import com.jkgug.example.storitest.domain.BankMovement

data class MovementDetailsUiState(
    val loadingContent: Boolean = false,
    val messageForUser: String? = null,
    val bankMovement: BankMovement = BankMovement()
)