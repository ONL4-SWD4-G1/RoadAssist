package com.example.roadassist.features.login.login.model

data class LoginUiState(
    val name: String = "",
    val password: String = "",
    val selectedTab: Int = 1,
    val nameError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false,
)
