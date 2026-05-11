package com.example.roadassist.features.booking.tracking.model

import com.example.roadassist.fakedata.ActiveBooking
import com.example.roadassist.fakedata.activeBooking

data class TrackingUiState(
    val booking: ActiveBooking = activeBooking,
)