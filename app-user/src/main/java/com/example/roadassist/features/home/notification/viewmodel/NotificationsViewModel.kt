package com.example.roadassist.features.home.notification.viewmodel

import androidx.lifecycle.ViewModel
import com.example.roadassist.fakedata.notifications
import com.example.roadassist.features.home.notification.model.NotificationsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NotificationsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(NotificationsUiState())
    val uiState: StateFlow<NotificationsUiState> = _uiState.asStateFlow()

    init {
        loadNotifications()
    }

    private fun loadNotifications() {
        _uiState.update {
            it.copy(
                notifications = notifications
            )
        }
    }
}