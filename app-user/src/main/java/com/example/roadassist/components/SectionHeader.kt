package com.example.roadassist.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roadassist.theme.TextPrimary

@Composable
fun SectionHeader(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: Int = 16,
    fontWeight: FontWeight = FontWeight.Bold,
    color: Color = TextPrimary
) {
    Text(
        text = text,
        fontWeight = fontWeight,
        fontSize = fontSize.sp,
        color = color,
        modifier = modifier.padding(bottom = 8.dp),
    )
}
