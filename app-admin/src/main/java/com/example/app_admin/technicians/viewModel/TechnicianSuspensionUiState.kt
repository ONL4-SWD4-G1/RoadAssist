package com.example.app_admin.technicians.viewModel

data class TechnicianSuspensionUiState(
    val techName: String = "",
    val techSpecialty: String = "",
    val completedJobs: Int = 0,
    val rating: Double = 0.0,
    val isPermanent: Boolean = false,
    val selectedDuration: String = "",
    val selectedReason: String = "",
    val adminNote: String = "",
    val activeOrdersCount: Int = 0,
    val isLoading: Boolean = false
)