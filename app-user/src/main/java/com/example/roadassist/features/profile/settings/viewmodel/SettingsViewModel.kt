package com.example.roadassist.features.profile.settings.viewmodel

import androidx.lifecycle.ViewModel
import com.example.roadassist.features.profile.settings.model.SettingsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SettingsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    fun onDarkModeToggled(enabled: Boolean) {
        _uiState.update { it.copy(darkMode = enabled) }
    }

    fun onNotificationsToggled(enabled: Boolean) {
        _uiState.update { it.copy(notifications = enabled) }
    }
}
