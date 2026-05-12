package com.example.app_admin.complaints.model

import androidx.compose.ui.graphics.Color

enum class ComplaintStatus(val label: String, val color: Color) {

    OPEN("مفتوحة", Color(0xFFC2410C)),
    UNDER_REVIEW("قيد المراجعة", Color(0xFF1D4ED8)),
    RESOLVED("تم الحل", Color(0xFF15803D)),
    TOP_PRIORITY("اولوية قصوى", Color(0xFFB91C1C));

    companion object {
        fun fromString(value: String?): ComplaintStatus =
            entries.find { it.name.equals(value, ignoreCase = true) } ?: OPEN
    }
}