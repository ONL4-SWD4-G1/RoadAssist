package com.example.app_admin.technicians.model

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