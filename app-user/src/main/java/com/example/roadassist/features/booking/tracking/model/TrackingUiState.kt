package com.example.roadassist.features.booking.tracking.model

import com.example.roadassist.fakedata.Technician

data class TrackingUiState(
    val technician: Technician? = null,
    val otp: String = "4821",
    val etaMinutes: Int = 5,
)
