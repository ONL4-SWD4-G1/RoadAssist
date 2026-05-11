package com.example.roadassist.features.home.notification.model

import com.example.roadassist.fakedata.NotificationItem

data class NotificationsUiState(
    val notifications: List<NotificationItem> = emptyList()
)