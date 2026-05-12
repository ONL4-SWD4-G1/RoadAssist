package com.example.app_admin.orders.model

import androidx.compose.ui.graphics.Color

enum class OrderStatus(val label: String, val color: Color) {
    NEW_REQUEST("طلب جديد", Color(0xFFF59E0B)),
    IN_PROGRESS("جاري التنفيذ", Color(0xFF3B82F6)),
    COMPLETED("مكتمل", Color(0xFF10B981)),
    CANCELLED("ملغي", Color(0xFFEF4444)),
    WAITING("بانتظار الفني", Color(0xFFA16207));

    companion object {
        fun fromString(value: String?): OrderStatus {
            return entries.find {
                it.label.equals(value, ignoreCase = true)
            } ?: NEW_REQUEST
        }
    }
}