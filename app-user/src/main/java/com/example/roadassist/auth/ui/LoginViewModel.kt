package com.example.roadassist.auth.ui

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.roadassist.R
import com.example.roadassist.auth.data.AuthRepository
import com.example.roadassist.auth.data.SessionStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val generalError: String? = null,
)

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val sessionStore = SessionStore(application)
    private val authRepository = AuthRepository()
    private val resources = application.resources

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(value: String) {
        _uiState.update {
            it.copy(email = value, emailError = null, generalError = null)
        }
    }

    fun onPasswordChange(value: String) {
        _uiState.update {
            it.copy(password = value, passwordError = null, generalError = null)
        }
    }

    fun onTogglePasswordVisibility() {
        _uiState.update { it.copy(passwordVisible = !it.passwordVisible) }
    }

    fun onSubmit(onSuccess: () -> Unit) {
        val state = _uiState.value
        val email = state.email.trim()
        val password = state.password

        var valid = true
        if (email.isEmpty()) {
            _uiState.update {
                it.copy(emailError = resources.getString(R.string.login_error_email_required))
            }
            valid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _uiState.update {
                it.copy(emailError = resources.getString(R.string.login_error_email_invalid))
            }
            valid = false
        }

        if (password.isEmpty()) {
            _uiState.update {
                it.copy(passwordError = resources.getString(R.string.login_error_password_required))
            }
            valid = false
        } else if (password.length < AuthRepository.MIN_PASSWORD_LENGTH) {
            _uiState.update {
                it.copy(
                    passwordError = resources.getString(
                        R.string.login_error_password_short,
                        AuthRepository.MIN_PASSWORD_LENGTH,
                    ),
                )
            }
            valid = false
        }

        if (!valid) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, generalError = null) }
            val result = authRepository.signIn(email, password)
            val error = result.exceptionOrNull()
            if (error != null) {
                val message = when (error.message) {
                    AuthRepository.ERROR_SHORT_PASSWORD -> resources.getString(
                        R.string.login_error_password_short,
                        AuthRepository.MIN_PASSWORD_LENGTH,
                    )

                    AuthRepository.ERROR_INVALID_EMAIL,
                    AuthRepository.ERROR_EMPTY_EMAIL,
                        -> resources.getString(R.string.login_error_email_invalid)

                    else -> resources.getString(R.string.login_error_generic)
                }
                _uiState.update {
                    it.copy(isLoading = false, generalError = message)
                }
                return@launch
            }
            // saveSession is suspend: this coroutine suspends until DataStore write completes.
            sessionStore.saveSession(email)
            _uiState.update { it.copy(isLoading = false) }
            onSuccess()
        }
    }
}