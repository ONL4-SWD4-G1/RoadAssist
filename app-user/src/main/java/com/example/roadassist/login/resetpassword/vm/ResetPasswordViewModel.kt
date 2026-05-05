package com.example.roadassist.login.resetpassword.vm

import androidx.lifecycle.ViewModel
import com.example.roadassist.login.resetpassword.model.ResetPasswordUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ResetPasswordViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ResetPasswordUiState())
    val uiState: StateFlow<ResetPasswordUiState> = _uiState.asStateFlow()

    fun onMobileNumberChange(value: String) {
        _uiState.update { it.copy(mobileNumber = value, mobileError = null) }
    }

    fun onGetOtpClick() {
        if (!validate()) return
        _uiState.update { it.copy(navigateToOtp = true) }
    }

    fun onNavigationHandled() {
        _uiState.update { it.copy(navigateToOtp = false) }
    }

    private fun validate(): Boolean {
        val mobileError = when {
            _uiState.value.mobileNumber.isBlank() -> "Mobile number is required"
            _uiState.value.mobileNumber.length < 10 -> "Invalid mobile number"
            else -> null
        }

        _uiState.update { it.copy(mobileError = mobileError) }

        return mobileError == null
    }
}