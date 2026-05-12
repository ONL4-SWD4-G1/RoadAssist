package com.example.roadassist.features.booking.technicians.profile.model

import com.example.roadassist.fakedata.Technician

data class TechnicianProfileUiState(
    val technician: Technician? = null,
    val showBookingSuccess: Boolean = false,
)
