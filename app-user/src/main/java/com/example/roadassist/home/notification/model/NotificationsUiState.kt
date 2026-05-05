package com.example.roadassist.home.notification.model

data class NotificationItem(
    val message: String,
    val time: String
)

data class NotificationsUiState(
    val notifications: List<NotificationItem> = emptyList()
)