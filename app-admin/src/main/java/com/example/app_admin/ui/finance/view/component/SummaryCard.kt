package com.example.app_admin.ui.finance.view.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_admin.R
import com.example.app_admin.ui.theme.LightText

@Composable
fun SummaryCard(
    modifier: Modifier,
    title: String,
    value: String,
    icon: ImageVector,
    color: Color,
    isOutlined: Boolean = false
) {
    Card(
        modifier = modifier.height(110.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = if (isOutlined) Color.White else color),
        border = if (isOutlined) BorderStroke(1.dp, color.copy(alpha = 0.3f)) else null
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            if (!isOutlined) {
                Image(
                    painter = painterResource(id = R.drawable.overlay),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = (10).dp, y = (-10).dp)
                        .size(90.dp),
                    alpha = 0.15f
                )
            }

            Box(modifier = Modifier.fillMaxSize().padding(12.dp)) {
                Icon(
                    icon, null,
                    modifier = Modifier.align(Alignment.TopEnd).size(24.dp),
                    tint = if (isOutlined) color else Color.White.copy(alpha = 0.7f)
                )
                Column(modifier = Modifier.align(Alignment.BottomEnd), horizontalAlignment = Alignment.End) {
                    Text(title, color =
                        if (isOutlined) LightText
                        else Color.White.copy(alpha = 0.9f), fontSize = 11.sp)
                    Text(value, color =
                        if (isOutlined) color
                        else Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
            }
        }
    }
}
