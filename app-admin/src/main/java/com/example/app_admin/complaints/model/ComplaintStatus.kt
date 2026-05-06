package com.example.app_admin.complaints.model

enum class ComplaintStatus { OPEN, UNDER_REVIEW, RESOLVED }

data class Complaint(
    val id: Int,
    val clientName: String,
    val technicianName: String,
    val issueType: String,
    val status: ComplaintStatus,
    val timeAgo: String
)

val sampleComplaints = listOf(
    Complaint(1, "سارة أحمد", "أحمد محمد", "بطارية / كهرباء", ComplaintStatus.OPEN, "منذ ساعتين"),
    Complaint(2, "محمد القحطاني", "ياسين عبدالله", "نظام المحرك", ComplaintStatus.UNDER_REVIEW, "منذ 5 ساعات"),
    Complaint(3, "عبدالله منصور", "فهد العتيبي", "إطارات / بنشر", ComplaintStatus.RESOLVED, "أمس")
)