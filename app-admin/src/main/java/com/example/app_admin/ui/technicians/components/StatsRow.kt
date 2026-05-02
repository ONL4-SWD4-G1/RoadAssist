package com.example.app_admin.ui.technicians.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StatsRow() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        StatCard("نشط الآن", "٤٢",
            "%٦٠+", Color(0xFFEC9513), Modifier.weight(1f))
        StatCard("قيد الانتظار", "١٥",
            "%٢٠-", Color(0xFFCBD5E1), Modifier.weight(1f))
        StatCard("تقييم منخفض", "٣",
            "%٠-", Color(0xFFF87171), Modifier.weight(1f))
    }
}

@Composable
fun StatCard(title: String, value: String, subtitle: String, borderColor: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(Color.White, RoundedCornerShape(12.dp))
            .drawBehind {
                val strokeWidth = 4.dp.toPx()
                drawLine(
                    color = borderColor,
                    start = Offset(0f, size.height - strokeWidth / 2),
                    end = Offset(size.width, size.height - strokeWidth / 2),
                    strokeWidth = strokeWidth
                )
            }
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(title, fontSize = 11.sp, color = Color(0xff64748B))
            Text(value, fontSize = 22.sp, fontWeight = FontWeight.Bold,
                color = Color(0xff1E293B))
            Text(subtitle, fontSize = 11.sp, color = Color(0xFF94A3B8))
        }
    }
}