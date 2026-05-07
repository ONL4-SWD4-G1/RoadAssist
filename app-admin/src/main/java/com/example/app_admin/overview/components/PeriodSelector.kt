package com.example.app_admin.overview.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PeriodSelector(
    periods: List<String>,
    selectedPeriod: Int,
    onPeriodSelected: (Int) -> Unit,
    activeColor: Color
) {

    Surface(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
        color = Color.White.copy(alpha = 0.08f),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.1f))
    ) {
        Row(modifier = Modifier.padding(4.dp)) {
            periods.forEachIndexed { index, title ->
                val isSelected = selectedPeriod == index
                Button(
                    onClick = { onPeriodSelected(index) },
                    modifier = Modifier
                        .weight(1f)
                        .height(38.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSelected) activeColor else Color.Transparent,
                        contentColor = if (isSelected) Color.White else Color.White.copy(alpha = 0.6f)
                    ),
                    shape = RoundedCornerShape(8.dp),
                    elevation = null,
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(title, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}