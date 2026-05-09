package com.example.roadassist.features.home.notification.vm

import androidx.lifecycle.ViewModel
import com.example.roadassist.features.home.notification.model.NotificationItem
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
                notifications = listOf(
                    NotificationItem("Your service provider is on the way. Arriving in 5 mins.", "Now"),
                    NotificationItem("5% discount on annual subscription!", "min ago"),
                    NotificationItem("10% discount on first service", "1 day ago")
                )
            )
        }
    }
}