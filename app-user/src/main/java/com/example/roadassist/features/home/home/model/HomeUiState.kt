package com.example.roadassist.features.home.home.model

import com.example.roadassist.fakedata.NearbyGarage
import com.example.roadassist.fakedata.ServiceItem


data class HomeUiState(
    val searchQuery: String = "",
    val services: List<ServiceItem> = emptyList(),
    val garages: List<NearbyGarage> = emptyList(),
)
