package com.example.app_admin.auth.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
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
fun LoginScreen(
    uiState: AuthUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordVisibilityToggle: () -> Unit,
    onLoginClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(IstighathaColors.Surface)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            AuthBackgroundDecoration()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))

            AuthBrandingHeader()

            Spacer(modifier = Modifier.height(48.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                AuthHeaderText(
                    title = "تسجيل الدخول",
                    subtitle = "أدخل بيانات حسابك للمتابعة"
                )

                Spacer(modifier = Modifier.height(32.dp))

                AuthInputField(
                    value = uiState.email,
                    onValueChange = onEmailChange,
                    placeholder = "اسم المستخدم أو البريد الإلكتروني",
                    icon = Icons.Default.Person,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                )

                Spacer(modifier = Modifier.height(16.dp))

                AuthInputField(
                    value = uiState.password,
                    onValueChange = onPasswordChange,
                    placeholder = "كلمة المرور",
                    icon = Icons.Default.Lock,
                    keyboardType = KeyboardType.Password,
                    isPassword = true,
                    isPasswordVisible = uiState.isPasswordVisible,
                    onPasswordVisibilityToggle = onPasswordVisibilityToggle,
                    focusedBorderColor = IstighathaColors.PrimaryContainer,
                    imeAction = ImeAction.Done,
                )

                Spacer(modifier = Modifier.height(24.dp))

                PrimaryButton(
                    text = "تسجيل الدخول",
                    onClick = onLoginClick,
                    isLoading = uiState.isLoading
                )

                Spacer(modifier = Modifier.height(16.dp))

                SecondaryLinkButton(
                    text = "نسيت كلمة المرور؟",
                    onClick = onForgotPasswordClick
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}