package com.example.app_admin.technicians.viewModel

import com.example.app_admin.sampleTechnicians
import com.example.app_admin.technicians.model.DetailFlow
import com.example.app_admin.technicians.model.Technician

data class TechnicianUiState(
    val allTechnicians: List<Technician> = sampleTechnicians,
    val selectedTab: Int = 0,
    val selectedTechnician: Technician? = null,
    val rejectionReason: String = "",
    val currentFlow: DetailFlow = DetailFlow.VIEW
)