package com.example.roadassist.features.services.battery.model

import com.example.roadassist.fakedata.VehicleDetails

data class BatteryUiState(
    val vehicleDetails: VehicleDetails = VehicleDetails(),
    val batteryCondition: String = "",
    val batteryType: String = "",
    val brandPreference: String = "",
    val budget: String = "",
)