package com.jkgug.example.storitest.utils

fun isValidEmail(email: String): Boolean {
    val emailRegex = Regex("""^[\w-\.]+@([\w-]+\.)+[\w-]{2,}$""")
    return emailRegex.matches(email)
}