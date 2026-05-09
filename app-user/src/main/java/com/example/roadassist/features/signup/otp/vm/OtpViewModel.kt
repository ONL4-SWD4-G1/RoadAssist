package com.example.roadassist.features.signup.otp.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roadassist.features.signup.otp.model.OtpUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OtpViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(OtpUiState())
    val uiState: StateFlow<OtpUiState> = _uiState.asStateFlow()

    private var timerJob: Job? = null

    init { startTimer() }

    // ---- OTP Input ----

    fun onOtp1Change(value: String) {
        _uiState.update { it.copy(otp1 = value, otpError = null) }
    }

    fun onOtp2Change(value: String) {
        _uiState.update { it.copy(otp2 = value, otpError = null) }
    }

    fun onOtp3Change(value: String) {
        _uiState.update { it.copy(otp3 = value, otpError = null) }
    }

    fun onOtp4Change(value: String) {
        _uiState.update { it.copy(otp4 = value, otpError = null) }
    }

    // ---- Timer ----

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            _uiState.update { it.copy(isTimerRunning = true) }
            while (_uiState.value.timerSeconds > 0) {
                delay(1_000)
                _uiState.update { it.copy(timerSeconds = it.timerSeconds - 1) }
            }
            _uiState.update { it.copy(isTimerRunning = false) }
        }
    }

    fun onResendOtp() {
        _uiState.update { it.copy(timerSeconds = 30, otpError = null) }
        startTimer()
    }

    // ---- Verify ----

    fun onVerifyClick() {
        if (!_uiState.value.isOtpComplete) {
            _uiState.update { it.copy(otpError = "Please enter the complete OTP") }
            return
        }
        _uiState.update { it.copy(showSuccessDialog = true) }
    }

    // ---- Success Dialog ----

    fun onExploreClick() {
        _uiState.update { it.copy(showSuccessDialog = false, navigateToHome = true) }
    }

    fun onNavigationHandled() {
        _uiState.update { it.copy(showSuccessDialog = false) }
    }


    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}