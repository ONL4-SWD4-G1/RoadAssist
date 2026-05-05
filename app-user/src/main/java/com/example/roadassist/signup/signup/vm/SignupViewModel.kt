package com.example.roadassist.signup.signup.vm

import androidx.lifecycle.ViewModel
import com.example.roadassist.signup.signup.model.SignupUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignupViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SignupUiState())
    val uiState: StateFlow<SignupUiState> = _uiState.asStateFlow()

    fun onNameChange(value: String) {
        _uiState.update { it.copy(name = value, nameError = null) }
    }

    fun onMobileChange(value: String) {
        _uiState.update { it.copy(mobileNumber = value, mobileError = null) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { it.copy(password = value, passwordError = null) }
    }

    fun onTabSelected(index: Int) {
        _uiState.update { it.copy(selectedTab = index) }
    }

    fun onSignupClick() {
        if (!validate()) return
        _uiState.update { it.copy(navigateToHome = true) }
    }

    fun onLoginTabClick() {
        _uiState.update { it.copy(navigateToLogin = true) }
    }

    fun onNavigationHandled() {
        _uiState.update {
            it.copy(navigateToHome = false, navigateToLogin = false)
        }
    }

    private fun validate(): Boolean {
        val state = _uiState.value
        var isValid = true

        val nameError = if (state.name.isBlank()) "Name is required" else null
        val mobileError = when {
            state.mobileNumber.isBlank() -> "Mobile number is required"
            state.mobileNumber.length < 10 -> "Invalid mobile number"
            else -> null
        }
        val passwordError = when {
            state.password.isBlank() -> "Password is required"
            state.password.length < 6 -> "Password must be at least 6 characters"
            else -> null
        }

        if (nameError != null || mobileError != null || passwordError != null) {
            isValid = false
        }

        _uiState.update {
            it.copy(
                nameError = nameError,
                mobileError = mobileError,
                passwordError = passwordError
            )
        }

        return isValid
    }
}