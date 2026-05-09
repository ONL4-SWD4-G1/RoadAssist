package com.example.roadassist.features.login.creatnewpassword.model
data class CreateNewPasswordUiState(
    val newPassword: String = "",
    val confirmPassword: String = "",
    val newPasswordError: String? = null,
    val confirmPasswordError: String? = null,
    val isLoading: Boolean = false,
    val showSuccessDialog: Boolean = false,
    val navigateToLogin: Boolean = false
)
