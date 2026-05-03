package com.example.app_admin.ui.technicians.view.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StatusBadges() {
    Row(modifier = Modifier.padding(end = 8.dp)) {
        Surface(color = Color(0xFF1D4ED8), shape = RoundedCornerShape(8.dp)) {
            Text("قيد المراجعة", color = Color.White, modifier = Modifier.
            padding(horizontal = 8.dp, vertical = 4.dp), fontSize = 11.sp)
        }
        Spacer(Modifier.width(4.dp))
        Surface(color = Color(0xFFBE123C), shape = RoundedCornerShape(8.dp)) {
            Text("أولوية قصوى", color = Color.White, modifier = Modifier.
            padding(horizontal = 8.dp, vertical = 4.dp), fontSize = 11.sp)
        }
    }
}