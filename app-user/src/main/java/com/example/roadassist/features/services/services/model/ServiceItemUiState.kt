package com.example.roadassist.features.services.services.model

import com.example.roadassist.fakedata.ServiceItem

data class ServicesUiState(
    val services: List<ServiceItem> = emptyList(),
)
