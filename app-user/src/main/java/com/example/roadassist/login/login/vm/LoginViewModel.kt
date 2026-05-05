package com.example.roadassist.login.login.vm

import androidx.lifecycle.ViewModel
import com.example.roadassist.login.login.model.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    // Input

    fun onNameChange(value: String) {
        _uiState.update { it.copy(name = value, nameError = null) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { it.copy(password = value, passwordError = null) }
    }

    fun onTabSelected(index: Int) {
        _uiState.update { it.copy(selectedTab = index) }
    }

    // Actions

    fun onLoginClick() {
        if (!validate()) return
        _uiState.update { it.copy(navigateToHome = true) }
    }

    fun onSignupTabClick() {
        _uiState.update { it.copy(navigateToSignup = true) }
    }

    fun onForgotPasswordClick() {
        _uiState.update { it.copy(navigateToForgotPassword = true) }
    }

    fun onNavigationHandled() {
        _uiState.update {
            it.copy(
                navigateToHome = false,
                navigateToSignup = false,
                navigateToForgotPassword = false
            )
        }
    }

    // Validation
    private fun validate(): Boolean {
        val state = _uiState.value

        val nameError = if (state.name.isBlank()) "Name is required" else null
        val passwordError = when {
            state.password.isBlank() -> "Password is required"
            state.password.length < 6 -> "Password must be at least 6 characters"
            else -> null
        }

        _uiState.update { it.copy(nameError = nameError, passwordError = passwordError) }

        return nameError == null && passwordError == null
    }
}