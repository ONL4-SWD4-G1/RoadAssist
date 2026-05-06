package com.example.app_admin.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_admin.ui.theme.DarkGray
import com.example.app_admin.ui.theme.DividerGray
import com.example.app_admin.ui.theme.LightBlueGray
import com.example.app_admin.ui.theme.NavyBlue
import com.example.app_admin.ui.theme.SoftWhite

@Composable
fun DocumentRow(title: String, isFile: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            .border(1.dp, DividerGray,
                RoundedCornerShape(12.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Icon(Icons.Default.KeyboardArrowLeft,
            contentDescription = null, tint = LightBlueGray)

        Spacer(modifier = Modifier.weight(1f))

        Column(horizontalAlignment = Alignment.End) {
            Text(title, fontSize = 13.sp,
                fontWeight = FontWeight.Bold, color = NavyBlue)
            Text("تم الرفع: ٢٠ أكتوبر ٢٠٢٣", fontSize = 11.sp, color = DarkGray)
        }
        Spacer(modifier = Modifier.width(12.dp))
        Box(
            modifier = Modifier.size(50.dp).clip(RoundedCornerShape(8.dp))
                .background(if (isFile) LightBlueGray else NavyBlue),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector =
                    if (isFile) Icons.Default.Description
                    else Icons.Default.Search,
                contentDescription = null,
                tint = SoftWhite,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}