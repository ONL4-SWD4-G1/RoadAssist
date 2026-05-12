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
import androidx.compose.material.icons.filled.Lock
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
import com.example.app_admin.auth.viewMode.AuthUiState
import com.example.app_admin.theme.IstighathaColors

@Composable
fun NewPasswordScreen(
    uiState: AuthUiState,
    onNewPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onNewPasswordVisibilityToggle: () -> Unit,
    onConfirmPasswordVisibilityToggle: () -> Unit,
    onSetPasswordClick: () -> Unit,
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
            title = "تعيين كلمة المرور",
            subtitle = "قم بتعيين كلمة مرور جديدة"
        )

        Spacer(modifier = Modifier.Companion.height(32.dp))

        Box(modifier = Modifier.Companion.padding(horizontal = 16.dp)) {
            AuthInputField(
                value = uiState.newPassword,
                onValueChange = onNewPasswordChange,
                placeholder = "كلمة المرور الجديدة",
                icon = Icons.Default.Lock,
                keyboardType = KeyboardType.Password,
                isPassword = true,
                isPasswordVisible = uiState.isNewPasswordVisible,
                onPasswordVisibilityToggle = onNewPasswordVisibilityToggle,
                imeAction = ImeAction.Next,
            )
        }

        Spacer(modifier = Modifier.Companion.height(16.dp))

        Box(modifier = Modifier.Companion.padding(horizontal = 16.dp)) {
            AuthInputField(
                value = uiState.confirmPassword,
                onValueChange = onConfirmPasswordChange,
                placeholder = "تأكيد كلمة المرور",
                icon = Icons.Default.Lock,
                keyboardType = KeyboardType.Password,
                isPassword = true,
                isPasswordVisible = uiState.isConfirmPasswordVisible,
                onPasswordVisibilityToggle = onConfirmPasswordVisibilityToggle,
                imeAction = ImeAction.Done,
            )
        }

        Spacer(modifier = Modifier.Companion.height(24.dp))

        Box(modifier = Modifier.Companion.padding(horizontal = 16.dp)) {
            PrimaryButton(
                text = "إعادة تعيين كلمة المرور",
                onClick = onSetPasswordClick,
                isLoading = uiState.isLoading
            )
        }

        Spacer(modifier = Modifier.Companion.weight(1f))

        AuthBackgroundDecoration()
    }
}