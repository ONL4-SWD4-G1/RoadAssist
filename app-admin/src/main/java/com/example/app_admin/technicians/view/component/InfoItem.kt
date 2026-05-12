package com.example.app_admin.technicians.view.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.app_admin.theme.LightBlueGray
import com.example.app_admin.theme.NavyBlue

@Composable
fun InfoItem(label: String, value: String,
             modifier: Modifier = Modifier,
             valueColor: Color = NavyBlue
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.End) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = LightBlueGray
        )
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = valueColor,
            textAlign = TextAlign.End
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InfoItemPreview() {
    InfoItem(label = "Status", value = "Completed")
}