package com.example.roadassist.signup.signup.model

data class SignupUiState(
    val name: String = "",
    val mobileNumber: String = "",
    val password: String = "",
    val selectedTab: Int = 0,
    val nameError: String? = null,
    val mobileError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val navigateToHome: Boolean = false,
    val navigateToLogin: Boolean = false
)