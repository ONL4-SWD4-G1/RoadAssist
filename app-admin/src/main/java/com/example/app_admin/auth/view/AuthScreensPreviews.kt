package com.example.app_admin.auth.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app_admin.auth.ui.ErrorMessage
import com.example.app_admin.auth.ui.SuccessMessage
import com.example.app_admin.auth.viewMode.AuthUiState
import com.example.app_admin.theme.IstighathaTheme

@Preview(name = "Auth — Login", showBackground = true, heightDp = 900, widthDp = 411, locale = "ar")
@Composable
private fun LoginScreenPreview() {
    IstighathaTheme {
        LoginScreen(
            uiState = AuthUiState(
                email = "admin@example.com",
                password = "secret12",
                isPasswordVisible = false,
            ),
            onEmailChange = {},
            onPasswordChange = {},
            onPasswordVisibilityToggle = {},
            onLoginClick = {},
            onForgotPasswordClick = {},
        )
    }
}

@Preview(name = "Auth — Forgot password", showBackground = true, heightDp = 900, widthDp = 411, locale = "ar")
@Composable
private fun ForgotPasswordScreenPreview() {
    IstighathaTheme {
        ForgotPasswordScreen(
            uiState = AuthUiState(email = "user@example.com"),
            onEmailChange = {},
            onSendClick = {},
            onBackClick = {},
        )
    }
}

@Preview(name = "Auth — OTP", showBackground = true, heightDp = 900, widthDp = 411, locale = "ar")
@Composable
private fun OTPScreenPreview() {
    IstighathaTheme {
        OTPScreen(
            uiState = AuthUiState(otpCode = "12"),
            onOTPChange = {},
            onVerifyClick = {},
            onResendClick = {},
        )
    }
}

@Preview(name = "Auth — New password", showBackground = true, heightDp = 900, widthDp = 411, locale = "ar")
@Composable
private fun NewPasswordScreenPreview() {
    IstighathaTheme {
        NewPasswordScreen(
            uiState = AuthUiState(
                newPassword = "newpass12",
                confirmPassword = "newpass12",
                isNewPasswordVisible = false,
                isConfirmPasswordVisible = false,
            ),
            onNewPasswordChange = {},
            onConfirmPasswordChange = {},
            onNewPasswordVisibilityToggle = {},
            onConfirmPasswordVisibilityToggle = {},
            onSetPasswordClick = {},
        )
    }
}

@Preview(name = "Auth — Success", showBackground = true, heightDp = 720, widthDp = 411, locale = "ar")
@Composable
private fun SuccessScreenPreview() {
    IstighathaTheme {
        SuccessScreen(onGoToLoginClick = {})
    }
}

@Preview(name = "Auth — Inline messages", showBackground = true, heightDp = 280, widthDp = 411, locale = "ar")
@Composable
private fun AuthInlineMessagesPreview() {
    IstighathaTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            ErrorMessage(message = "البريد الإلكتروني مطلوب")
            Spacer(modifier = Modifier.height(12.dp))
            SuccessMessage(message = "تم إرسال رمز التحقق")
        }
    }
}
