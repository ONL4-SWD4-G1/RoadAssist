package com.example.roadassist.features.services.flattyre.model

import com.example.roadassist.fakedata.VehicleDetails

data class FlatTyreUiState(
    val vehicleDetails: VehicleDetails = VehicleDetails(),
    val vehicleMake: String = "",
    val tyreSize: String = "",
    val spareAvailable: String = "",
)