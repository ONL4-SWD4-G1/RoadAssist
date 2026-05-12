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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.app_admin.auth.view.component.AuthBackgroundDecoration
import com.example.app_admin.auth.view.component.AuthBrandingHeader
import com.example.app_admin.auth.view.component.AuthHeaderText
import com.example.app_admin.auth.view.component.AuthInputField
import com.example.app_admin.auth.view.component.PrimaryButton
import com.example.app_admin.auth.view.component.SecondaryLinkButton
import com.example.app_admin.auth.viewMode.AuthUiState
import com.example.app_admin.theme.IstighathaColors

@Composable
fun ForgotPasswordScreen(
    uiState: AuthUiState,
    onEmailChange: (String) -> Unit,
    onSendClick: () -> Unit,
    onBackClick: () -> Unit,
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
            title = "نسيت كلمة المرور",
            subtitle = "أدخل بريدك الإلكتروني لاستعادة كلمة المرور"
        )

        Spacer(modifier = Modifier.Companion.height(32.dp))

        Box(modifier = Modifier.Companion.padding(horizontal = 16.dp)) {
            AuthInputField(
                value = uiState.email,
                onValueChange = onEmailChange,
                placeholder = "البريد الإلكتروني",
                icon = Icons.Default.Person,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done,
            )
        }

        Spacer(modifier = Modifier.Companion.height(24.dp))

        Box(modifier = Modifier.Companion.padding(horizontal = 16.dp)) {
            PrimaryButton(
                text = "إرسال رابط الاستعادة",
                onClick = onSendClick,
                isLoading = uiState.isLoading
            )
        }

        Spacer(modifier = Modifier.Companion.height(16.dp))

        SecondaryLinkButton(
            text = "العودة إلى تسجيل الدخول",
            onClick = onBackClick
        )

        Spacer(modifier = Modifier.Companion.weight(1f))

        AuthBackgroundDecoration()
    }
}