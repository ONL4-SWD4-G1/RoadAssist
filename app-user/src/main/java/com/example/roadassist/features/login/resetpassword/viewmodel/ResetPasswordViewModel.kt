package com.example.roadassist.features.login.resetpassword.viewmodel

import androidx.lifecycle.ViewModel
import com.example.roadassist.features.login.resetpassword.model.ResetPasswordUiState
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

    fun validate(): Boolean {
        val mobileError = when {
            _uiState.value.mobileNumber.isBlank() -> "Mobile number is required"
            _uiState.value.mobileNumber.length < 10 -> "Invalid mobile number"
            else -> null
        }
        _uiState.update { it.copy(mobileError = mobileError) }
        return mobileError == null
    }
}
