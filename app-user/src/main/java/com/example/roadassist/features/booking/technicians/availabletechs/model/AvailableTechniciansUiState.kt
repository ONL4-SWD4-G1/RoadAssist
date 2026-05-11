package com.example.roadassist.features.booking.technicians.availabletechs.model

import com.example.roadassist.fakedata.Technician

data class AvailableTechniciansUiState(
    val allTechnicians: List<Technician> = emptyList(),
    val filteredTechnicians: List<Technician> = emptyList(),
    val searchQuery: String = "",
    val selectedFilter: String = "All",
    val filters: List<String> = listOf("All", "Engine", "Brakes", "Electrical"),
)
