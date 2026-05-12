package com.example.app_admin.auth.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.app_admin.auth.view.component.AuthBackgroundDecoration
import com.example.app_admin.auth.view.component.AuthBrandingHeader
import com.example.app_admin.auth.view.component.AuthHeaderText
import com.example.app_admin.auth.view.component.OTPInputField
import com.example.app_admin.auth.view.component.PrimaryButton
import com.example.app_admin.auth.view.component.SecondaryLinkButton
import com.example.app_admin.auth.viewMode.AuthUiState
import com.example.app_admin.theme.IstighathaColors

@Composable
fun OTPScreen(
    uiState: AuthUiState,
    onOTPChange: (String) -> Unit,
    onVerifyClick: () -> Unit,
    onResendClick: () -> Unit,
    modifier: Modifier = Modifier.Companion
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(IstighathaColors.Surface)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Companion.CenterHorizontally
    ) {
        AuthBrandingHeader()

        AuthHeaderText(
            title = "رمز التحقق",
            subtitle = "أدخل رمز التحقق المرسل إلى بريدك"
        )

        Spacer(modifier = Modifier.Companion.height(48.dp))

        Box(modifier = Modifier.Companion.padding(horizontal = 32.dp)) {
            OTPInputField(
                value = uiState.otpCode,
                onValueChange = onOTPChange,
                count = 4,
                borderColor = IstighathaColors.PrimaryContainer,
                focusedBorderColor = IstighathaColors.Primary
            )
        }

        Spacer(modifier = Modifier.Companion.height(48.dp))

        Box(modifier = Modifier.Companion.padding(horizontal = 16.dp)) {
            PrimaryButton(
                text = "تحقق",
                onClick = onVerifyClick,
                isLoading = uiState.isLoading
            )
        }

        Spacer(modifier = Modifier.Companion.height(16.dp))

        SecondaryLinkButton(
            text = "إعادة إرسال الرمز",
            onClick = onResendClick,
            textColor = IstighathaColors.PrimaryContainer
        )

        Spacer(modifier = Modifier.Companion.weight(1f))

        AuthBackgroundDecoration()
    }
}