package com.example.app_admin.user.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserSuspensionViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UserSuspensionUiState())
    val uiState = _uiState.asStateFlow()

    fun togglePermanent(isPermanent: Boolean) {
        _uiState.update { it.copy(isPermanent = isPermanent) }
    }

    fun updateDuration(duration: String) {
        _uiState.update { it.copy(selectedDuration = duration) }
    }

    fun updateComment(comment: String) {
        _uiState.update { it.copy(adminComment = comment) }
    }

    fun confirmSuspension() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            delay(1500) // Simulate API call
            _uiState.update { it.copy(isLoading = false, isSuccess = true) }
        }
    }
}