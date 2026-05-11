package com.example.roadassist.features.services.engine.model

import com.example.roadassist.fakedata.VehicleDetails

data class EngineUiState(
    val vehicleDetails: VehicleDetails = VehicleDetails(),
    val selectedIssue: String = "",
    val otherDetail: String = "",
)