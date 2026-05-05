package com.example.roadassist.login.creatnewpassword.vm

import androidx.lifecycle.ViewModel
import com.example.roadassist.login.creatnewpassword.model.CreateNewPasswordUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CreateNewPasswordViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CreateNewPasswordUiState())
    val uiState: StateFlow<CreateNewPasswordUiState> = _uiState.asStateFlow()

    fun onNewPasswordChange(value: String) {
        _uiState.update { it.copy(newPassword = value, newPasswordError = null) }
    }

    fun onConfirmPasswordChange(value: String) {
        _uiState.update { it.copy(confirmPassword = value, confirmPasswordError = null) }
    }

    fun onUpdateClick() {
        if (!validate()) return
        _uiState.update { it.copy(showSuccessDialog = true) }
    }

    fun onExploreClick() {
        _uiState.update { it.copy(showSuccessDialog = false, navigateToLogin = true) }
    }

    fun onNavigationHandled() {
        _uiState.update { it.copy(navigateToLogin = false) }
    }

    private fun validate(): Boolean {
        val state = _uiState.value

        val newPasswordError = when {
            state.newPassword.isBlank() -> "Password is required"
            state.newPassword.length < 6 -> "Password must be at least 6 characters"
            else -> null
        }

        val confirmPasswordError = when {
            state.confirmPassword.isBlank() -> "Please confirm your password"
            state.confirmPassword != state.newPassword -> "Passwords do not match"
            else -> null
        }

        _uiState.update {
            it.copy(
                newPasswordError = newPasswordError,
                confirmPasswordError = confirmPasswordError
            )
        }

        return newPasswordError == null && confirmPasswordError == null
    }
}