package com.example.app_admin.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StatusBadge(
    label: String,
    color: Color,
    modifier: Modifier = Modifier,
    backgroundColor: Color? = null

) {

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(color = backgroundColor ?: color.copy(alpha = 0.15f))
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Text(
            text = label,
            color = color,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StatusBadgePreview() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        StatusBadge(label = "ACTIVE", color = Color(0xFF4CAF50))
        StatusBadge(label = "PENDING", color = Color(0xFFFFC107))
        StatusBadge(label = "ERROR", color = Color(0xFFF44336))
        StatusBadge(
            label = "CUSTOM BG",
            color = Color.White,
            backgroundColor = Color.Black
        )
    }
}