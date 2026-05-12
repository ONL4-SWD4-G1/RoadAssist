package com.example.roadassist.features.profile.userprofile.viewmodel

import androidx.lifecycle.ViewModel
import com.example.roadassist.features.profile.userprofile.model.ProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun onLogoutRequested() {
        _uiState.update { it.copy(showLogoutDialog = true) }
    }

    fun onLogoutDismissed() {
        _uiState.update { it.copy(showLogoutDialog = false) }
    }
}
