package com.example.app_admin.auth.viewMode

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.app_admin.auth.data.AdminSessionStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModelFactory(
    private val application: Application,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(AdminSessionStore(application)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

sealed class AuthScreen {
    object Login : AuthScreen()
    object ForgotPassword : AuthScreen()
    object OTP : AuthScreen()
    object NewPassword : AuthScreen()
    object Success : AuthScreen()
}

data class AuthUiState(
    val email: String = "",
    val password: String = "",
    val newPassword: String = "",
    val confirmPassword: String = "",
    val otpCode: String = "",
    val isPasswordVisible: Boolean = false,
    val isNewPasswordVisible: Boolean = false,
    val isConfirmPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null,
    val currentScreen: AuthScreen = AuthScreen.Login,
)

class AuthViewModel(
    private val sessionStore: AdminSessionStore,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState

    fun updateEmail(newEmail: String) {
        _uiState.value = _uiState.value.copy(email = newEmail, errorMessage = null)
    }

    fun updatePassword(newPassword: String) {
        _uiState.value = _uiState.value.copy(password = newPassword, errorMessage = null)
    }

    fun updateNewPassword(newPassword: String) {
        _uiState.value = _uiState.value.copy(newPassword = newPassword, errorMessage = null)
    }

    fun updateConfirmPassword(newPassword: String) {
        _uiState.value = _uiState.value.copy(confirmPassword = newPassword, errorMessage = null)
    }

    fun updateOtpCode(newCode: String) {
        _uiState.value = _uiState.value.copy(otpCode = newCode.filter { it.isDigit() }.take(4))
    }

    fun togglePasswordVisibility() {
        _uiState.value = _uiState.value.copy(
            isPasswordVisible = !_uiState.value.isPasswordVisible
        )
    }

    fun toggleNewPasswordVisibility() {
        _uiState.value = _uiState.value.copy(
            isNewPasswordVisible = !_uiState.value.isNewPasswordVisible
        )
    }

    fun toggleConfirmPasswordVisibility() {
        _uiState.value = _uiState.value.copy(
            isConfirmPasswordVisible = !_uiState.value.isConfirmPasswordVisible
        )
    }

    fun navigateToScreen(screen: AuthScreen) {
        _uiState.value = _uiState.value.copy(
            currentScreen = screen,
            errorMessage = null,
            successMessage = null
        )
    }

    fun login() {
        val state = _uiState.value

        if (state.email.isEmpty()) {
            _uiState.value = _uiState.value.copy(errorMessage = "البريد الإلكتروني مطلوب")
            return
        }
        if (state.password.isEmpty()) {
            _uiState.value = _uiState.value.copy(errorMessage = "كلمة المرور مطلوبة")
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                kotlinx.coroutines.delay(1500)

                sessionStore.saveSession(state.email)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    successMessage = "تم تسجيل الدخول بنجاح"
                )

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "حدث خطأ في تسجيل الدخول"
                )
            }
        }
    }

    fun requestPasswordReset() {
        val state = _uiState.value

        if (state.email.isEmpty()) {
            _uiState.value = _uiState.value.copy(errorMessage = "البريد الإلكتروني مطلوب")
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                kotlinx.coroutines.delay(1500)

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    currentScreen = AuthScreen.OTP,
                    successMessage = "تم إرسال رمز التحقق"
                )

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "فشل في إرسال رمز التحقق"
                )
            }
        }
    }

    fun verifyOTP() {
        val state = _uiState.value

        if (state.otpCode.length < 4) {
            _uiState.value = _uiState.value.copy(errorMessage = "يجب إدخال 4 أرقام")
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                kotlinx.coroutines.delay(1000)

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    currentScreen = AuthScreen.NewPassword
                )

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "رمز غير صحيح"
                )
            }
        }
    }

    fun setNewPassword() {
        val state = _uiState.value

        if (state.newPassword.isEmpty()) {
            _uiState.value = _uiState.value.copy(errorMessage = "كلمة المرور الجديدة مطلوبة")
            return
        }
        if (state.confirmPassword.isEmpty()) {
            _uiState.value = _uiState.value.copy(errorMessage = "تأكيد كلمة المرور مطلوب")
            return
        }
        if (state.newPassword != state.confirmPassword) {
            _uiState.value = _uiState.value.copy(errorMessage = "كلمات المرور غير متطابقة")
            return
        }
        if (state.newPassword.length < 8) {
            _uiState.value = _uiState.value.copy(errorMessage = "يجب أن تكون كلمة المرور 8 أحرف على الأقل")
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                kotlinx.coroutines.delay(1500)

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    currentScreen = AuthScreen.Success,
                    successMessage = "تم تحديث كلمة المرور بنجاح"
                )

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "فشل في تحديث كلمة المرور"
                )
            }
        }
    }

    fun resendOTP() {
        val state = _uiState.value

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                kotlinx.coroutines.delay(1000)


                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    successMessage = "تم إرسال رمز جديد"
                )

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "فشل في إعادة إرسال الرمز"
                )
            }
        }
    }

    fun resetAndGoToLogin() {
        _uiState.value = AuthUiState(currentScreen = AuthScreen.Login)
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }

    fun clearSuccess() {
        _uiState.value = _uiState.value.copy(successMessage = null)
    }
}