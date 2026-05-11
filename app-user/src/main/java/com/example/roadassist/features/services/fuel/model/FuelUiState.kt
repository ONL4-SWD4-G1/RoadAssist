package com.example.roadassist.features.services.fuel.model

import com.example.roadassist.fakedata.VehicleDetails

data class FuelUiState(
    val vehicleDetails: VehicleDetails = VehicleDetails(),
    val fuelType: String = "",
    val fuelAmount: String = "",
)