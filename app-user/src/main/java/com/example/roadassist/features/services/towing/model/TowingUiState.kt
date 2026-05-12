package com.example.roadassist.features.services.towing.model

import com.example.roadassist.fakedata.VehicleDetails

data class TowingUiState(
    val vehicleDetails: VehicleDetails = VehicleDetails(),
    val breakdownReason: String = "",
    val vehicleCondition: String = "",
    val destination: String = "",
)