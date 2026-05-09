package com.example.app_admin.complaints.model

data class ComplaintTechnicianUiState(
    val name: String = "محمد سامي",
    val specialty: String = "فني كهرباء سيارات",
    val experience: String = "خبرة ٥ سنوات",
    val phone: String = "+٩٦٦ ٥٠ XXX XXXX",
    val rating: String = "٤.٩",
    val completedJobs: String = "١٥٠",
    val responseTime: String = "١٢",
    val totalEarnings: String = "٢,٤٥٠ ر.س",
    val earningsGrowth: String = "+١٢٪ هذا الشهر",
    val isOnline: Boolean = true,
    val offers: List<TechnicianOffer> = emptyList(),
    val isLoading: Boolean = false
)

data class TechnicianOffer(
    val id: Int,
    val title: String,
    val timeAgo: String,
    val description: String,
    val status: String = "قيد المراجعة"
)