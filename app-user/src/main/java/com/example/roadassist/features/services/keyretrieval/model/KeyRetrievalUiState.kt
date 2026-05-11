package com.example.roadassist.features.services.keyretrieval.model

import com.example.roadassist.fakedata.VehicleDetails

data class KeyRetrievalUiState(
    val vehicleDetails: VehicleDetails = VehicleDetails(),
    val lockManufacturer: String = "",
    val ownershipUploaded: Boolean = false,
    val specialInstructions: String = "",
)