package com.example.roadassist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.roadassist.auth.data.SessionStore
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class RootUiState(
    val sessionLoaded: Boolean = false,
    val isLoggedIn: Boolean = false,
)

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val sessionStore = SessionStore(application)

    val rootState: StateFlow<RootUiState> = sessionStore.isLoggedIn
        .map { loggedIn ->
            RootUiState(sessionLoaded = true, isLoggedIn = loggedIn)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = RootUiState(sessionLoaded = false, isLoggedIn = false),
        )

    fun logout() {
        viewModelScope.launch {
            sessionStore.clearSession()
        }
    }
}
