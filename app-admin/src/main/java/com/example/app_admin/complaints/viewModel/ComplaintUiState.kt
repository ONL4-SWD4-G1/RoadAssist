package com.example.app_admin.complaints.viewModel

import com.example.app_admin.complaints.model.Complaint
import com.example.app_admin.sampleComplaints

data class ComplaintUiState(
    val complaints: List<Complaint> = sampleComplaints,
    val selectedComplaint: Complaint? = null,
    val showWarningScreen: Boolean = false,
    val showUserProfile: Boolean = false,
)