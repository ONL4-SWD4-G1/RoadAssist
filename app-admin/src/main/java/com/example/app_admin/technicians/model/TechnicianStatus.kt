package com.example.app_admin.technicians.model

import androidx.compose.ui.graphics.Color

enum class TechnicianStatus(val label: String, val color: Color) {
    ACTIVE("نشط", Color(0xFF15803D)),
    WAITING("قيد الانتظار", Color(0xFFA16207)),
    SUSPENDED("موقوف", Color(0xFFB91C1C)),
    NEW_REQUEST("طلب جديد", Color(0xFFC2410C));

    companion object {
        fun fromString(value: String?): TechnicianStatus =
            entries.find { it.name.equals(value, ignoreCase = true) } ?: NEW_REQUEST
    }
}