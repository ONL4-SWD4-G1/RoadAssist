package com.example.app_admin.auth.ui

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app_admin.auth.LocalAdminLogout
import com.example.app_admin.auth.view.ForgotPasswordScreen
import com.example.app_admin.auth.view.LoginScreen
import com.example.app_admin.auth.view.NewPasswordScreen
import com.example.app_admin.auth.view.OTPScreen
import com.example.app_admin.auth.view.SuccessScreen
import com.example.app_admin.auth.viewMode.AuthScreen
import com.example.app_admin.auth.viewMode.AuthViewModel
import com.example.app_admin.auth.viewMode.AuthViewModelFactory
import com.example.app_admin.auth.viewModel.AdminRootViewModel
import com.example.app_admin.root.MainScreen
import com.example.app_admin.theme.IstighathaColors
import com.example.app_admin.theme.IstighathaTheme
import com.example.app_admin.theme.RoadAssistTheme

@Composable
fun AdminApp() {
    val rootVm: AdminRootViewModel = viewModel()
    val rootState by rootVm.rootState.collectAsStateWithLifecycle()

    RoadAssistTheme {
        when {
            !rootState.sessionLoaded -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }

            rootState.isLoggedIn -> {
                CompositionLocalProvider(LocalAdminLogout provides { rootVm.logout() }) {
                    MainScreen()
                }
            }

            else -> {
                IstighathaTheme {
                    AuthFlowHost()
                }
            }
        }
    }
}

@Composable
private fun AuthFlowHost() {
    val app = LocalContext.current.applicationContext as Application
    val factory = remember(app) { AuthViewModelFactory(app) }
    val vm: AuthViewModel = viewModel(factory = factory)
    val uiState by vm.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        uiState.errorMessage?.let { msg ->
            ErrorMessage(
                message = msg,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            )
        }
        uiState.successMessage?.let { msg ->
            SuccessMessage(
                message = msg,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            )
        }
        when (uiState.currentScreen) {
            AuthScreen.Login -> LoginScreen(
                uiState = uiState,
                onEmailChange = vm::updateEmail,
                onPasswordChange = vm::updatePassword,
                onPasswordVisibilityToggle = vm::togglePasswordVisibility,
                onLoginClick = vm::login,
                onForgotPasswordClick = { vm.navigateToScreen(AuthScreen.ForgotPassword) },
            )

            AuthScreen.ForgotPassword -> ForgotPasswordScreen(
                uiState = uiState,
                onEmailChange = vm::updateEmail,
                onSendClick = vm::requestPasswordReset,
                onBackClick = { vm.navigateToScreen(AuthScreen.Login) },
            )

            AuthScreen.OTP -> OTPScreen(
                uiState = uiState,
                onOTPChange = vm::updateOtpCode,
                onVerifyClick = vm::verifyOTP,
                onResendClick = vm::resendOTP,
            )

            AuthScreen.NewPassword -> NewPasswordScreen(
                uiState = uiState,
                onNewPasswordChange = vm::updateNewPassword,
                onConfirmPasswordChange = vm::updateConfirmPassword,
                onNewPasswordVisibilityToggle = vm::toggleNewPasswordVisibility,
                onConfirmPasswordVisibilityToggle = vm::toggleConfirmPasswordVisibility,
                onSetPasswordClick = vm::setNewPassword,
            )

            AuthScreen.Success -> SuccessScreen(
                onGoToLoginClick = vm::resetAndGoToLogin,
            )
        }
    }
}

@Composable
fun ErrorMessage(
    message: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = IstighathaColors.ErrorContainer,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(12.dp)
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodySmall,
            color = IstighathaColors.Error
        )
    }
}

@Composable
fun SuccessMessage(
    message: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFE8F5E9),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
            )
            .padding(12.dp)
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodySmall,
            color = IstighathaColors.Success
        )
    }
}