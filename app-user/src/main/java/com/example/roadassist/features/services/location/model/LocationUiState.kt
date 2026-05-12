package com.example.roadassist.features.services.location.model

data class LocationUiState(
    val landmark: String = "",
    val searchQuery: String = "",
    val pinnedLat: Double = 30.0444,
    val pinnedLng: Double = 31.2357,
)