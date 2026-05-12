package com.example.roadassist.auth.data

import kotlinx.coroutines.delay

class AuthRepository {

    suspend fun signIn(email: String, password: String): Result<Unit> {
        delay(750)
        val trimmed = email.trim()
        if (trimmed.isEmpty()) {
            return Result.failure(IllegalStateException(ERROR_EMPTY_EMAIL))
        }
        if (!trimmed.contains('@')) {
            return Result.failure(IllegalStateException(ERROR_INVALID_EMAIL))
        }
        if (password.length < MIN_PASSWORD_LENGTH) {
            return Result.failure(IllegalStateException(ERROR_SHORT_PASSWORD))
        }
        return Result.success(Unit)
    }

    companion object {
        const val MIN_PASSWORD_LENGTH = 6
        const val ERROR_EMPTY_EMAIL = "error_empty_email"
        const val ERROR_INVALID_EMAIL = "error_invalid_email"
        const val ERROR_SHORT_PASSWORD = "error_short_password"
    }
}
