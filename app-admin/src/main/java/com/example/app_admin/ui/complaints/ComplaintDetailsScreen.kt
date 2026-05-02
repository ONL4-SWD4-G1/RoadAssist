package com.example.app_admin.ui.complaints

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_admin.R
import com.example.app_admin.ui.technicians.components.StatusBadges

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComplaintDetailsScreen(onBack: () -> Unit,
                           onNavigateToWarning: () -> Unit,
                           onNavigateToProfile: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.fillMaxWidth().padding(end = 16.dp)
                    ) {
                        Text("شكوى #8241", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        Text("بتاريخ: ٢٤ مايو ٢٠٢٤", fontSize = 11.sp,
                            color = Color.White.copy(alpha = 0.7f))
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "", tint = Color.White)
                    }
                },
                actions = { StatusBadges() },
                colors = TopAppBarDefaults.topAppBarColors(containerColor =
                    Color(0xFF1E293B))
            )
        },
        bottomBar = {
            ComplaintActionButtons(onWarningClick = onNavigateToWarning)
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding).background(Color(0xFFF8FAFC)),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { ComplaintSummarySection() }
            item { UsersSection(onShowUserProfile = onNavigateToProfile) }
            item { TimelineSection() }
            item { AttachmentsSection() }
        }
    }
}

//@Composable
//fun StatusBadges() {
//    Row(modifier = Modifier.padding(end = 8.dp)) {
//        Surface(color = Color(0xFF1D4ED8), shape = RoundedCornerShape(8.dp)) {
//            Text("قيد المراجعة", color = Color.White, modifier = Modifier.
//            padding(horizontal = 8.dp, vertical = 4.dp), fontSize = 11.sp)
//        }
//        Spacer(Modifier.width(4.dp))
//        Surface(color = Color(0xFFBE123C), shape = RoundedCornerShape(8.dp)) {
//            Text("أولوية قصوى", color = Color.White, modifier = Modifier.
//            padding(horizontal = 8.dp, vertical = 4.dp), fontSize = 11.sp)
//        }
//    }
//}

@Composable
fun ComplaintSummarySection() {
    val context = LocalContext.current
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFE2E8F0))
    ) {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.End) {
            SectionHeader(Icons.Default.Info, "ملخص الشكوى")
            Text(
                "تأخر الفني في الوصول لأكثر من ٤٥ دقيقة عن الموعد المحدد، وعند الوصول لم يقم بفك الإطار بطريقة صحيحة مما تسبب في خدش الجنط.",
                fontSize = 13.sp, color = Color(0xFF64748B), textAlign = TextAlign.End,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text("رقم الطلب المرتبط: ORD-5521-X", color = Color(0xFFF97316), fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))
            Card(
                modifier = Modifier.fillMaxWidth().height(150.dp).clickable {
                    val gmmIntentUri =
                        Uri.parse("geo:24.8138,46.6333?q=حي الياسمين، الرياض")
                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                    context.startActivity(mapIntent)
                },
                shape = RoundedCornerShape(12.dp)
            ) {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.mappreview),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Surface(
                        modifier = Modifier.
                        align(Alignment.BottomEnd).padding(8.dp),
                        color = Color.White,
                        shape = RoundedCornerShape(8.dp),
                        shadowElevation = 2.dp
                    ) {
                        Text("حي الياسمين، الرياض", fontSize = 10.sp, modifier = Modifier.padding(6.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun UsersSection(onShowUserProfile: () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        UserDetailCard("صاحب الشكوى (العميل)", "عبدالرحمن القحطاني", "عضو منذ ٢٠٢٢ | تقييم ٤.٨", Icons.Default.Person, onProfileClick = onShowUserProfile)
        UserDetailCard("الفني المعني", "محمد سامي", "فني إطارات | سجل الشكاوي: ٢", Icons.Default.Engineering, onProfileClick = {})
    }
}

@Composable
fun UserDetailCard(sectionTitle: String, name: String,
                   info: String, icon: ImageVector,
                   onProfileClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFE2E8F0))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically) {
                Text(sectionTitle, fontSize = 12.sp, color = Color(0xFF64748B))
                Spacer(Modifier.width(4.dp))
                Icon(icon, null, modifier = Modifier.size(14.dp), tint = Color(0xFF64748B))
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                OutlinedButton(
                    onClick = onProfileClick,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.height(35.dp),
                    border = BorderStroke(1.dp, Color(0xFFF97316))
                ) {
                    Text("الملف الكامل", fontSize = 11.sp, color = Color(0xFFF97316))
                }
                Spacer(Modifier.weight(1f))
                Column(horizontalAlignment = Alignment.End) {
                    Text(name, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Text(info, fontSize = 11.sp, color = Color(0xFF64748B))
                }
                Spacer(Modifier.width(12.dp))
                Box(modifier = Modifier.size(45.dp).
                clip(CircleShape).background(Color(0xFFF1F5F9))) {
                    Icon(Icons.Default.Person,
                        null,
                        modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}

@Composable
fun TimelineSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFE2E8F0))
    ) {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.End) {
            SectionHeader(Icons.Default.Timeline, "الخط الزمني للموقع")
            Spacer(modifier = Modifier.height(12.dp))
            TimelineItem("بداية التحرك",
                "١٤:٣٠ - المسافة المتبقية ٥ كم", isFirst = true)
            TimelineItem("وصول الفني",
                "١٥:١٥ (تأخير ١٥ دقيقة)", isAlert = true)
            TimelineItem("إنهاء الطلب",
                "١٥:٤٥ - حي الياسمين", isLast = true)
        }
    }
}

@Composable
fun AttachmentsSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFE2E8F0))
    ) {
        Column(modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.End) {
            SectionHeader(Icons.Default.Image, "المرفقات (٢)")
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End) {
                AttachmentImage(R.drawable.overlay)
                Spacer(Modifier.width(12.dp))
                AttachmentImage(R.drawable.overlay)
            }
        }
    }
}

@Composable
fun SectionHeader(icon: ImageVector, title: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(title, fontSize = 14.sp,
            fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
        Spacer(Modifier.width(8.dp))
        Icon(icon,
            "", tint = Color(0xFFF97316),
            modifier = Modifier.size(18.dp))
    }
}

@Composable
fun TimelineItem(title: String, time: String, isFirst: Boolean = false, isLast: Boolean = false, isAlert: Boolean = false) {
    Row(modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.End) {
        Column(horizontalAlignment = Alignment.End,
            modifier = Modifier.padding(end = 16.dp)) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 13.sp)
            Text(time, fontSize = 11.sp, color =
                if (isAlert) Color(0xFFF97316)
                else Color(0xFF94A3B8))
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(modifier = Modifier.size(12.dp).
            clip(CircleShape).background(if
                    (isAlert) Color(0xFFF97316)
            else Color(0xFF22C55E)))
            if (!isLast) {
                Box(modifier = Modifier.width(2.dp).fillMaxHeight().background(Color(0xFFE2E8F0)))
            }
        }
    }
}

@Composable
fun AttachmentImage(resId: Int) {
    Image(
        painter = painterResource(id = resId),
        contentDescription = null,
        modifier = Modifier.size(90.dp).clip(RoundedCornerShape(12.dp)).border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(12.dp)),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ComplaintActionButtons(onWarningClick: () -> Unit) {
    Column(modifier = Modifier.background(Color.White).padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            ActionButton(Modifier.weight(1f), "استرداد", Color(0xFF22C55E), Icons.Default.History, {})
            ActionButton(Modifier.weight(1f), "خصم", Color(0xFFF97316), Icons.Default.ContentCut, {})
            ActionButton(Modifier.weight(1f), "تحذير", Color(0xFFEAB308), Icons.Default.Warning, onWarningClick)
            ActionButton(Modifier.weight(1f), "إيقاف الفني", Color(0xFFEF4444), Icons.Default.Block, {})
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E293B)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("إغلاق الشكوى نهائياً", fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ActionButton(modifier: Modifier, text: String, color: Color, icon: ImageVector, onClick: () -> Unit) {
    Surface(
        modifier = modifier.height(40.dp).clickable { onClick() },
        color = color.copy(alpha = 0.1f),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, color)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Text(text, color = color, fontSize = 10.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.width(4.dp))
            Icon(icon, null, tint = color, modifier = Modifier.size(12.dp))
        }
    }
}