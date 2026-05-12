package com.example.app_admin.more.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_admin.auth.LocalAdminLogout
import com.example.app_admin.more.view.component.MoreItemCard

@Composable
fun MoreScreen() {
    val onLogout = LocalAdminLogout.current
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
                text = "القائمة والإعدادات",
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
                SectionTitle(title = "إدارة الخدمات والنظام")
                Spacer(modifier = Modifier.height(12.dp))
                MoreItemCard(
                    title = "فئات الخدمات",
                    subTitle = "تعديل وإضافة خدمات الطريق",
                    icon = Icons.Default.Category,
                    color = Color(0xFF1E293B)
                )
                MoreItemCard(
                    title = "الشكاوى والنزاعات",
                    subTitle = "مراجعة طلبات الدعم والاعتراضات",
                    icon = Icons.Default.Gavel,
                    color = Color(0xFF1E293B),
                    badgeCount = 12
                )
                MoreItemCard(
                    title = "التحكم في التقييمات",
                    subTitle = "إدارة تقييمات العملاء والمزودين",
                    icon = Icons.Default.Star,
                    color = Color(0xFF1E293B)
                )
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                SectionTitle("تفضلات التطبيق")
                Spacer(modifier = Modifier.height(12.dp))
                MoreItemCard(
                    title = "إعدادات التطبيق",
                    subTitle = "النظام، اللغات، والإشعارات",
                    icon = Icons.Default.Settings,
                    color = Color(0xFF1E293B)
                )
                MoreItemCard(
                    title = "الأمان والخصوصية",
                    subTitle = "إدارة صلاحيات الوصول",
                    icon = Icons.Default.Shield,
                    color = Color(0xFF1E293B)
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
            item {
                Button(
                    onClick = onLogout,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFF1F2)),
                    shape = RoundedCornerShape(12.dp),
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Logout,
                            contentDescription = "",
                            tint = Color(0xFFDC2626)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "تسجيل الخروج",
                            color = Color(0xFFDC2626),
                            fontWeight = FontWeight.Bold
                        )
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
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "أحمد محمود",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF1E293B)
                )
                Text(
                    text = "مدير النظام الرئيسي",
                    fontSize = 14.sp,
                    color = Color(0xFF64748B)
                )
                Spacer(modifier = Modifier.height(8.dp))
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
                    border = BorderStroke(2.dp, Color.White)
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "",
                        modifier = Modifier.padding(16.dp),
                        tint = Color.White
                    )
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
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 14.sp,
        color = Color(0xFF94A3B8),
        fontWeight = FontWeight.Medium
    )
}