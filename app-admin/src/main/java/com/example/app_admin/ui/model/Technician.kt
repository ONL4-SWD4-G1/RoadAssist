package com.example.app_admin.model

enum class TechnicianStatus {
    ACTIVE,
    WAITING,
    SUSPENDED,
    NEW_REQUEST
}

data class Technician(
    val id: Int,
    val name: String,
    val specialty: String,
    val status: TechnicianStatus,
    val rating: Double,
    val completedJobs: Int,
    val city: String,
    val phone: String,
    val experience: Int,
    val hasCar: Boolean = false
)

val sampleTechnicians = listOf(
    Technician(1, "أحمد محمد",
        "فني كهرباء سيارات",
        TechnicianStatus.ACTIVE,
        4.9, 150
        , "الرياض", "+966501234567", 7),
    Technician(2, "ياسين عبدالله", "ميكانيكي محركات ديزل", TechnicianStatus.WAITING, 4.7, 88, "جدة", "+966502234567", 5),
    Technician(3, "خالد عمر", "فني إطارات وبطاريات", TechnicianStatus.SUSPENDED, 3.2, 12, "الدمام", "+966503234567", 2),
    Technician(4, "مازن العتيبي", "فني تبريد وتكييف سيارات", TechnicianStatus.NEW_REQUEST, 0.0, 0, "الدمام", "+966504234567", 8, hasCar = true)
)