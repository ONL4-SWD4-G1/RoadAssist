package com.example.app_admin.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_admin.ui.complaints.model.ComplaintStatus

@Composable
fun ComplaintStatusBadge(status: ComplaintStatus) {
    val (text, color) = when (status) {
        ComplaintStatus.OPEN -> "مفتوحة" to Color(0xFFC2410C)
        ComplaintStatus.UNDER_REVIEW -> "قيد المراجعة" to Color(0xFF1D4ED8)
        ComplaintStatus.RESOLVED -> "تم الحل" to Color(0xFF15803D)
    }
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(color.copy(alpha = 0.15f))
            .padding(horizontal = 8.dp, vertical = 3.dp)
    ) {
        Text(text, color = color, fontSize = 11.sp, fontWeight = FontWeight.Medium)
    }
}