package com.example.app_admin.auth.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_admin.auth.data.AdminSessionStore
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class AdminRootUiState(
    val sessionLoaded: Boolean = false,
    val isLoggedIn: Boolean = false,
)

class AdminRootViewModel(application: Application) : AndroidViewModel(application) {

    private val sessionStore = AdminSessionStore(application)

    val rootState: StateFlow<AdminRootUiState> = sessionStore.isLoggedIn
        .map { loggedIn ->
            AdminRootUiState(sessionLoaded = true, isLoggedIn = loggedIn)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = AdminRootUiState(sessionLoaded = false, isLoggedIn = false),
        )

    fun logout() {
        viewModelScope.launch {
            sessionStore.clearSession()
        }
    }
}
