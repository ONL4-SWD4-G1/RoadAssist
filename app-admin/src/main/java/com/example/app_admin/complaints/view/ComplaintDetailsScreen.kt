package com.example.app_admin.complaints.view

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.ContentCut
import androidx.compose.material.icons.filled.Engineering
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.example.app_admin.R
import com.example.app_admin.complaints.model.Complaint
import com.example.app_admin.sampleComplaints
import com.example.app_admin.shared.StatusBadge

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComplaintDetailsScreen(
    complaint: Complaint,
    onBack: () -> Unit,
    onNavigateToWarning: () -> Unit,
    onNavigateToDeduction: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToComplainant: () -> Unit,
    onNavigateToRefund: () -> Unit,
    onNavigateToSuspension: () -> Unit
) {
    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            TopAppBar(
                title = {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 16.dp)
                    ) {
                        Text(
                            text = "شكوى #8241",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "بتاريخ: ٢٤ مايو ٢٠٢٤",
                            fontSize = 11.sp,
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    Row(modifier = Modifier.padding(end = 8.dp)) {
                        StatusBadge(
                            label = complaint.status.label,
                            color = complaint.status.color,
                            backgroundColor = Color(0xFFDBEAFE)
                        )

                        Spacer(Modifier.width(4.dp))

                        StatusBadge(
                            label = complaint.status.label,
                            color = complaint.status.color,
                            backgroundColor = Color(0xFFFEE2E2)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor =
                        Color(0xFF1E293B)
                ),
                windowInsets = WindowInsets(0, 0, 0, 0)
            )
        },
        bottomBar = {
            ComplaintActionButtons(
                onWarningClick = onNavigateToWarning,
                onDeductionClick = onNavigateToDeduction,
                onSuspensionClick = onNavigateToSuspension,
                onRefundClick = onNavigateToRefund

            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF8FAFC)),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { ComplaintSummarySection() }
            item {
                UsersSection(
                    onShowUserProfile = onNavigateToProfile,
                    onShowComplainantProfile = onNavigateToComplainant
                )
            }
            item { TimelineSection() }
            item { AttachmentsSection() }
        }
    }
}

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
            Text(
                "رقم الطلب المرتبط: ORD-5521-X",
                color = Color(0xFFF97316),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clickable {
                        val gmmIntentUri =
                            "geo:24.8138,46.6333?q=حي الياسمين، الرياض".toUri()
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
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(8.dp),
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
fun UsersSection(
    onShowUserProfile: () -> Unit,
    onShowComplainantProfile: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        UserDetailCard(
            sectionTitle = "صاحب الشكوى (العميل)",
            name = "عبدالرحمن القحطاني",
            info = "عضو منذ ٢٠٢٢ | تقييم ٤.٨",
            icon = Icons.Default.Person,
            onProfileClick = onShowUserProfile
        )
        UserDetailCard(
            sectionTitle = "الفني المعني",
            name = "محمد سامي",
            info = "فني إطارات | سجل الشكاوي: ٢",
            icon = Icons.Default.Engineering,
            onProfileClick = onShowComplainantProfile
        )
    }
}

@Composable
fun UserDetailCard(
    sectionTitle: String,
    name: String,
    info: String,
    icon: ImageVector,
    onProfileClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFE2E8F0))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = sectionTitle,
                    fontSize = 12.sp,
                    color = Color(0xFF64748B)
                )

                Spacer(Modifier.width(4.dp))

                Icon(
                    imageVector = icon,
                    null,
                    modifier = Modifier.size(14.dp),
                    tint = Color(0xFF64748B)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = onProfileClick,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.height(35.dp),
                    border = BorderStroke(1.dp, Color(0xFFF97316))
                ) {
                    Text(
                        text = "الملف الكامل",
                        fontSize = 11.sp,
                        color = Color(0xFFF97316)
                    )
                }

                Spacer(Modifier.weight(1f))

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Text(
                        text = info,
                        fontSize = 11.sp,
                        color = Color(0xFF64748B)
                    )
                }

                Spacer(Modifier.width(12.dp))

                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFF1F5F9))
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.Center)
                    )
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
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.End
        ) {
            SectionHeader(icon = Icons.Default.Timeline, title = "الخط الزمني للموقع")

            Spacer(modifier = Modifier.height(12.dp))

            TimelineItem(
                title = "بداية التحرك",
                time = "١٤:٣٠ - المسافة المتبقية ٥ كم",
                isFirst = true
            )
            TimelineItem(
                title = "وصول الفني",
                time = "١٥:١٥ (تأخير ١٥ دقيقة)",
                isAlert = true
            )
            TimelineItem(
                title = "إنهاء الطلب",
                time = "١٥:٤٥ - حي الياسمين",
                isLast = true
            )
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
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.End
        ) {
            SectionHeader(icon = Icons.Default.Image, title = "المرفقات (٢)")

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                AttachmentImage(R.drawable.overlay)

                Spacer(Modifier.width(12.dp))

                AttachmentImage(R.drawable.overlay)
            }
        }
    }
}

@Composable
fun SectionHeader(
    icon: ImageVector,
    title: String
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1E293B)
        )

        Spacer(Modifier.width(8.dp))

        Icon(
            imageVector = icon,
            contentDescription = "",
            tint = Color(0xFFF97316),
            modifier = Modifier.size(18.dp)
        )
    }
}

@Composable
fun TimelineItem(
    title: String,
    time: String,
    isFirst: Boolean = false,
    isLast: Boolean = false,
    isAlert: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.End
    ) {
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.padding(end = 16.dp)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
            )
            Text(
                text = time,
                fontSize = 11.sp,
                color = if (isAlert) Color(0xFFF97316)
                else Color(0xFF94A3B8)
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(
                        if (isAlert) Color(0xFFF97316)
                        else Color(0xFF22C55E)
                    )
            )
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .fillMaxHeight()
                        .background(Color(0xFFE2E8F0))
                )
            }
        }
    }
}

@Composable
fun AttachmentImage(resId: Int) {
    Image(
        painter = painterResource(id = resId),
        contentDescription = null,
        modifier = Modifier
            .size(90.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp, Color(0xFFE2E8F0),
                shape = RoundedCornerShape(12.dp)
            ),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ComplaintActionButtons(
    onWarningClick: () -> Unit,
    onDeductionClick: () -> Unit,
    onSuspensionClick: () -> Unit,
    onRefundClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ActionButton(
                modifier = Modifier.weight(1f),
                text = "استرداد",
                color = Color(0xFF22C55E),
                icon = Icons.Default.History,
                onClick = onRefundClick
            )
            ActionButton(
                modifier = Modifier.weight(1f),
                text = "خصم",
                color = Color(0xFFF97316),
                icon = Icons.Default.ContentCut,
                onClick = onDeductionClick
            )
            ActionButton(
                modifier = Modifier.weight(1f),
                text = "تحذير",
                color = Color(0xFFEAB308),
                icon = Icons.Default.Warning,
                onClick = onWarningClick
            )
            ActionButton(
                modifier = Modifier.weight(1f),
                text = "إيقاف الفني",
                color = Color(0xFFEF4444),
                icon = Icons.Default.Block,
                onClick = onSuspensionClick
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1E293B)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "إغلاق الشكوى نهائياً",
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ActionButton(
    modifier: Modifier,
    text: String,
    color: Color,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .height(40.dp)
            .clickable { onClick() },
        color = color.copy(alpha = 0.1f),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, color)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                color = color,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.width(4.dp))

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(12.dp)
            )
        }
    }
}

@Preview(showBackground = true, locale = "ar")
@Composable
fun PreviewComplaintDetails() {
    ComplaintDetailsScreen(
        complaint = sampleComplaints[0],
        onBack = {},
        onNavigateToWarning = {},
        onNavigateToDeduction = {},
        onNavigateToProfile = {},
        onNavigateToComplainant = {},
        onNavigateToSuspension = {},
        onNavigateToRefund = {}
    )
}