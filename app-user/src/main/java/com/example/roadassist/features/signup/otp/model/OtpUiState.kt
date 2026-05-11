package com.example.roadassist.features.signup.otp.model

data class OtpUiState(
    val otp1: String = "",
    val otp2: String = "",
    val otp3: String = "",
    val otp4: String = "",
    val timerSeconds: Int = 30,
    val isTimerRunning: Boolean = true,
    val otpError: String? = null,
    val isLoading: Boolean = false,
    val showSuccessDialog: Boolean = false,
) {
    val fullOtp: String get() = otp1 + otp2 + otp3 + otp4
    val isOtpComplete: Boolean get() = fullOtp.length == 4
    val formattedTimer: String get() = "00:${timerSeconds.toString().padStart(2, '0')}"
}
