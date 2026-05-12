package com.example.roadassist.features.profile.history.model

import com.example.roadassist.fakedata.ServiceHistoryItem

data class ServiceHistoryUiState(
    val items: List<ServiceHistoryItem> = emptyList(),
    val isLoading: Boolean = false,
)
