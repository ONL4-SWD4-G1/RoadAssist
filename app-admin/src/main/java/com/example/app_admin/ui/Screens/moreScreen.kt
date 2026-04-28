package com.example.app_admin.ui.Screens
import android.provider.CalendarContract
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_admin.ui.theme.*

@Composable
fun MoreScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffF8F7F6))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "القائمة والإعدادات",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E293B)
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            horizontalAlignment = Alignment.End
        ) {

            item {
                ProfileHeaderCard()
                Spacer(modifier = Modifier.height(32.dp))
            }


            item {
                SectionTitle("إدارة الخدمات والنظام")
                Spacer(modifier = Modifier.height(12.dp))
                MoreItemCard("فئات الخدمات",
                    "تعديل وإضافة خدمات الطريق",
                    Icons.Default.Category, Color(0xFF1E293B))
                MoreItemCard("الشكاوى والنزاعات",
                    "مراجعة طلبات الدعم والاعتراضات",
                    Icons.Default.Gavel, Color(0xFF1E293B),
                    badgeCount = 12)
                MoreItemCard("التحكم في التقييمات",
                    "إدارة تقييمات العملاء والمزودين",
                    Icons.Default.Star, Color(0xFF1E293B))
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                SectionTitle("تفضلات التطبيق")
                Spacer(modifier = Modifier.height(12.dp))
                MoreItemCard("إعدادات التطبيق",
                    "النظام، اللغات، والإشعارات",
                    Icons.Default.Settings, Color(0xFF1E293B))
                MoreItemCard("الأمان والخصوصية",
                    "إدارة صلاحيات الوصول",
                    Icons.Default.Shield, Color(0xFF1E293B))
                Spacer(modifier = Modifier.height(32.dp))
            }
            item {
                Button(
                    onClick = {  },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.
                    buttonColors(containerColor = Color(0xFFFFF1F2)),
                    shape = RoundedCornerShape(12.dp),
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Logout, "", tint = Color(0xFFDC2626))
                        Spacer(Modifier.width(8.dp))
                        Text("تسجيل الخروج", color = Color(0xFFDC2626),
                            fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileHeaderCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors =  CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(24.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(horizontalAlignment = Alignment.End) {
            Text("أحمد محمود", fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF1E293B))
            Text("مدير النظام الرئيسي", fontSize = 14.sp, color = Color(0xFF64748B))
            Spacer(Modifier.height(8.dp))
            Surface(
                color = Color(0xFFFEF3C7),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    "لوحة تحكم استغاثة",
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    fontSize = 12.sp,
                    color = Color(0xFFD97706),
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))

        Box {
            Surface(
                modifier = Modifier.size(80.dp),
                shape = CircleShape,
                color = Color.LightGray,
                border = androidx.compose.
                foundation.BorderStroke(2.dp, Color.White)
            ) {
                Icon(Icons.Default.Person,
                    "",
                    modifier = Modifier.padding(16.dp),
                    tint = Color.White)
            }
            Box(
                modifier = Modifier
                    .size(14.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF22C55E))
                    .align(Alignment.BottomStart)
                    .offset(x = 4.dp, y = (-4).dp)
            )
        }
    }}
}

@Composable
fun SectionTitle(title: String) {
    Text(title, fontSize = 14.sp,
        color = Color(0xFF94A3B8),
        fontWeight = FontWeight.Medium)
}

@Composable
fun MoreItemCard(title: String,
                 subTitle: String,
                 icon: ImageVector,
                 color: Color,
                 badgeCount: Int = 0) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.ArrowBackIosNew, null, modifier = Modifier.size(14.dp), tint = Color(0xFF94A3B8))

            if (badgeCount > 0) {
                Spacer(Modifier.width(8.dp))
                Surface(color = Color(0xFFEF4444), shape = CircleShape) {
                    Text(
                        badgeCount.toString(),
                        color = Color.White,
                        fontSize = 10.sp,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(horizontalAlignment = Alignment.End) {
                Text(title, fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E293B))
                Text(subTitle, fontSize = 12.sp, color = Color(0xFF94A3B8))
            }

            Spacer(modifier = Modifier.width(16.dp))

            Surface(
                modifier = Modifier.size(44.dp),
                shape = RoundedCornerShape(10.dp),
                color = color
            ) {
                Icon(icon,
                    "",
                    modifier = Modifier.padding(10.dp),
                    tint = Color(0xFFFF2960D))
            }
        }
    }
}