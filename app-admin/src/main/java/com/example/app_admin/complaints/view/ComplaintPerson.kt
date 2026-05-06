package com.example.app_admin.complaints.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.BatteryChargingFull
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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

@Composable
fun CompliantPerson(onBack: () -> Unit, onWarningClick: () -> Unit) {
    Scaffold(
        bottomBar = { UserActionButtons(onWarningClick = onWarningClick) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF8FAFC)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { HeaderSection(onBack) }
            item {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ContactInfoCard()
                    UserStatsCard()
                    RecentOrdersSection()
                    UserReviewsSection()
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
fun HeaderSection(onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .background(Color(0xFF1E293B))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, null, tint = Color.White)
            }
            IconButton(onClick = { }) {
                Icon(Icons.Default.Edit, null, tint = Color.White)
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF97316).copy(alpha = 0.2f))
                    .border(4.dp, Color(0xFFF97316), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Person, null, modifier = Modifier.size(60.dp), tint = Color.White)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text("عبدالرحمن القحطاني", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF22C55E)))
                Spacer(modifier = Modifier.width(6.dp))
                Text("حساب نشط", color = Color(0xFF22C55E), fontSize = 12.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text("|", color = Color.White.copy(alpha = 0.5f))
                Spacer(modifier = Modifier.width(8.dp))
                Text("منذ سبتمبر ٢٠٢٣"
                    , color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun ContactInfoCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-30).dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            ContactItem(Icons.Default.Phone, "رقم الهاتف", "٠٥٠١٢٣٤٥٦٧")
            HorizontalDivider(color = Color(0xFFF1F5F9))
            ContactItem(Icons.Default.LocationOn, "المدينة", "الرياض")
            HorizontalDivider(color = Color(0xFFF1F5F9))
            ContactItem(Icons.Default.Email, "البريد الإلكتروني", "a.qahtani@example.com")
        }
    }
}

@Composable
fun ContactItem(icon: ImageVector, label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically) {
        Column(horizontalAlignment = Alignment.End,
            modifier = Modifier.weight(1f)) {
            Text(label, fontSize = 11.sp, color = Color(0xFF94A3B8))
            Text(value, fontSize = 14.sp, fontWeight = FontWeight.Bold,
                color = Color(0xFF1E293B))
        }
        Spacer(modifier = Modifier.width(12.dp))
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFF1F5F9)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, null, modifier = Modifier.size(20.dp), tint = Color(0xFF64748B))
        }
    }
}

@Composable
fun UserStatsCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-20).dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        StatItem(Modifier.weight(1f), "إجمالي الطلبات", "١٨", null)
        StatItem(Modifier.weight(1f), "متوسط التقييم", "٤.٨", Icons.Default.Star)
        StatItem(Modifier.weight(1f), "الشكاوى", "١", null, isNegative = true)
    }
}

@Composable
fun StatItem(modifier: Modifier, label: String, value: String, icon: ImageVector?, isNegative: Boolean = false) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFE2E8F0))
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(label, fontSize = 10.sp, color = Color(0xFF64748B))
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (icon != null) Icon(icon, null, modifier = Modifier.size(12.dp), tint = Color(0xFFEAB308))
                Text(
                    text = value,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isNegative) Color(0xFFEF4444) else Color(0xFF1E293B)
                )
            }
        }
    }
}

@Composable
fun RecentOrdersSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text("عرض الكل", color = Color(0xFFF97316),
                fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Text("سجل الطلبات الأخير", fontSize = 14.sp,
                fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(12.dp))
        OrderItem("سحب سيارة تعطلت", "١٥ يناير ٢٠٢" +
                "٤ | ٢٥٠ ر.س", Icons.Default.DirectionsCar)
        Spacer(modifier = Modifier.height(8.dp))
        OrderItem("شحن بطارية", "٠٢ يناير ٢" +
                "٠٢٤ | ٨٠ ر.س", Icons.Default.BatteryChargingFull)
    }
}

@Composable
fun OrderItem(title: String, subtitle: String, icon: ImageVector) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFE2E8F0))
    ) {
        Row(modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack,
                null, modifier = Modifier.size(16.dp),
                tint = Color(0xFF94A3B8))
            Text("مكتمل", color = Color(0xFF22C55E), fontSize = 11.sp,
                modifier = Modifier.padding(start = 8.dp))
            Spacer(modifier = Modifier.weight(1f))
            Column(horizontalAlignment = Alignment.End) {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                Text(subtitle, fontSize = 11.sp, color = Color(0xFF94A3B8))
            }
            Spacer(modifier = Modifier.width(12.dp))
            Box(modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFFFF7ED)), contentAlignment = Alignment.Center) {
                Icon(icon, null, tint = Color(0xFFF97316))
            }
        }
    }
}

@Composable
fun UserReviewsSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text("التقييمات التي قدمها", fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End)
        Spacer(modifier = Modifier.height(12.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.
            cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp,
                Color(0xFFE2E8F0))
        ) {
            Column(modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.End) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("منذ أسبوعين", fontSize = 11.sp,
                        color = Color(0xFF94A3B8))
                    Spacer(Modifier.width(8.dp))
                    Row { repeat(5) { Icon(Icons.Default.Star,
                        null,
                        modifier = Modifier.size(14.dp), tint = Color(0xFFEAB308)) } }
                }
                Text(
                    "خدمة ممتازة وسريعة جداً. الفني كان محترفاً ووصل في الوقت المحدد.",
                    fontSize = 13.sp, textAlign = TextAlign.End,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("تقييم للفني: أحمد محمد",
                        fontSize = 11.sp, color = Color(0xFF64748B))
                    Spacer(Modifier.width(8.dp))
                    Box(modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFCBD5E1)))
                }
            }
        }
    }
}

@Composable
fun UserActionButtons(onWarningClick: () -> Unit) { // أضفنا البارامتر هنا
    Row(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = {},
            modifier = Modifier
                .weight(1.5f)
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFEF2F2)),
            border = BorderStroke(1.dp, Color(0xFFFEE2E2)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(Icons.Default.Delete, null, tint = Color(0xFFEF4444), modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(8.dp))
            Text("حذف الحساب", color = Color(0xFFEF4444), fontWeight = FontWeight.Bold)
        }

        OutlinedButton(
            onClick = {},
            modifier = Modifier
                .weight(1f)
                .height(48.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color(0xFFF8FAFC))
        ) {
            Icon(Icons.Default.Block, null, tint = Color(0xFF1E293B), modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(8.dp))
            Text("إيقاف", color = Color(0xFF1E293B))
        }

        OutlinedButton(
            onClick = onWarningClick, // قمنا بربط الزر بالوظيفة هنا
            modifier = Modifier
                .weight(1f)
                .height(48.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color(0xFFF8FAFC))
        ) {
            Icon(Icons.Default.Warning, null, tint = Color(0xFFEAB308), modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(8.dp))
            Text("تحذير", color = Color(0xFF1E293B))
        }
    }
}