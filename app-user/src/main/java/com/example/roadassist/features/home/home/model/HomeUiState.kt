package com.example.roadassist.features.home.home.model

import androidx.compose.ui.graphics.vector.ImageVector

data class ServiceItem(
    val icon: ImageVector,
    val label: String
)

data class GarageItem(
    val name: String,
    val location: String,
    val distance: String,
    val rating: Float
)

data class HomeUiState(
    val searchQuery: String = "",
    val services: List<ServiceItem> = emptyList(),
    val garages: List<GarageItem> = emptyList(),
    val navigateToProfile: Boolean = false,
    val navigateToNotifications: Boolean = false
)