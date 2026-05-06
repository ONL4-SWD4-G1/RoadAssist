package com.example.app_admin.complaints.model

data class Complaint(
    val id: Int,
    val clientName: String,
    val technicianName: String,
    val issueType: String,
    val status: ComplaintStatus,
    val timeAgo: String
)