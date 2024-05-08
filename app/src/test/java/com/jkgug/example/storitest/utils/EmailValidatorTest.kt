package com.jkgug.example.storitest.utils

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class EmailValidatorTest {

    @Test
    fun isValidEmail_validEmail_returnsTrue() {
        // Arrange
        val email = "john.doe@example.com"

        // Act
        val result = isValidEmail(email)

        // Assert
        assertTrue(result)
    }

    @Test
    fun isValidEmail_invalidEmail_returnsFalse() {
        // Arrange
        val email = "johndoe@example"

        // Act
        val result = isValidEmail(email)

        // Assert
        assertFalse(result)
    }

    @Test
    fun isValidEmail_emptyEmail_returnsFalse() {
        // Arrange
        val email = ""

        // Act
        val result = isValidEmail(email)

        // Assert
        assertFalse(result)
    }

}