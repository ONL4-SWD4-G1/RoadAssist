package com.example.roadassist.login.resetpassword.model

data class ResetPasswordUiState(
    val mobileNumber: String = "",
    val mobileError: String? = null,
    val navigateToOtp: Boolean = false
)