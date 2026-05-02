package com.example.app_admin.ui.shared


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_admin.ui.theme.*

@Composable
fun SectionCard(title: String, icon: ImageVector, content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = SoftWhite),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()) {
                Text(title, fontWeight = FontWeight.Bold,
                    fontSize = 15.sp, color = NavyBlue)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(icon,
                    contentDescription = null,
                    tint = NavyBlue,
                    modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            content()
        }
    }
}

//@Composable
//fun InfoItem(label: String, value: String,
//             modifier: Modifier = Modifier,
//             valueColor: Color = NavyBlue) {
//    Column(modifier = modifier, horizontalAlignment = Alignment.End) {
//        Text(label, fontSize = 12.sp, color = LightBlueGray)
//        Text(value, fontSize = 14.sp,
//            fontWeight = FontWeight.Bold,
//            color = valueColor,
//            textAlign = TextAlign.End)
//    }
//}

//@Composable
//fun DocumentRow(title: String, isFile: Boolean = false) {
//    Row(
//        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
//            .border(1.dp, DividerGray,
//                RoundedCornerShape(12.dp))
//            .padding(8.dp),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.End
//    ) {
//        Icon(Icons.Default.KeyboardArrowLeft,
//            contentDescription = null, tint = LightBlueGray)
//
//        Spacer(modifier = Modifier.weight(1f))
//
//        Column(horizontalAlignment = Alignment.End) {
//            Text(title, fontSize = 13.sp,
//                fontWeight = FontWeight.Bold, color = NavyBlue)
//            Text("تم الرفع: ٢٠ أكتوبر ٢٠٢٣", fontSize = 11.sp, color = DarkGray)
//        }
//        Spacer(modifier = Modifier.width(12.dp))
//        Box(
//            modifier = Modifier.size(50.dp).clip(RoundedCornerShape(8.dp))
//                .background(if (isFile) LightBlueGray else NavyBlue),
//            contentAlignment = Alignment.Center
//        ) {
//            Icon(
//                imageVector =
//                    if (isFile) Icons.Default.Description
//                    else Icons.Default.Search,
//                contentDescription = null,
//                tint = SoftWhite,
//                modifier = Modifier.size(20.dp)
//            )
//        }
//    }
//}