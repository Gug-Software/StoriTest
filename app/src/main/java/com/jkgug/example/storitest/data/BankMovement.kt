package com.jkgug.example.storitest.data

data class BankMovement(
    val idTransaction: String = "",
    val title: String = "",
    val date: String = "",
    val amount: String = "",
    val description: String = "",
    val type: String = "",
    val status: Boolean = false
) {
    var idFromFireStore: String = ""
}