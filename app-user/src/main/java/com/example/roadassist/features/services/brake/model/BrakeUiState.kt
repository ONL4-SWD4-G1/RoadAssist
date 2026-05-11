package com.example.roadassist.features.services.brake.model

import com.example.roadassist.fakedata.VehicleDetails

data class BrakeUiState(
    val vehicleDetails: VehicleDetails = VehicleDetails(),
    val selectedIssue: String = "",
    val otherDetail: String = "",
)