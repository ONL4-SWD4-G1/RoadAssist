package com.example.app_admin.complaints.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Engineering
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.PauseCircle
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app_admin.R
import com.example.app_admin.complaints.model.ComplaintTechnicianUiState
import com.example.app_admin.complaints.model.TechnicianOffer
import com.example.app_admin.complaints.view.component.ComplaintActionButton
import com.example.app_admin.complaints.viewModel.TechnicianDetailViewModel
import com.example.app_admin.shared.RoadAssistTopAppBar
import com.example.app_admin.shared.StatusBadge
import com.example.app_admin.theme.DarkNavy
import com.example.app_admin.theme.PrimaryOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComplainantTechnicianScreen(
    onBack: () -> Unit,
    viewModel: TechnicianDetailViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold(
            topBar = {
                RoadAssistTopAppBar(
                    title = uiState.name,
                    containerColor = DarkNavy,
                    contentColor = Color.White,
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.Share, null, tint = Color.White)
                        }
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.Edit, null, tint = Color.White)
                        }
                    }
                )
            },
            bottomBar = { TechnicianActionBottomBar() },
            containerColor = Color(0xFFF8F7F6)
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                item { TechnicianInfoCard(state = uiState) }

                item {
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        StatSmallCard(
                            title = "وقت الاستجابة",
                            value = uiState.responseTime,
                            unit = "دقيقة",
                            modifier = Modifier.weight(1f)
                        )
                        StatSmallCard(
                            title = "المهام",
                            value = uiState.completedJobs,
                            modifier = Modifier.weight(1f),
                            icon = Icons.Default.CheckCircle,
                            iconColor = Color(0xFF3B82F6)
                        )
                        StatSmallCard(
                            title = "التقييم",
                            value = uiState.rating,
                            modifier = Modifier.weight(1f),
                            icon = Icons.Default.Star,
                            iconColor = PrimaryOrange
                        )
                    }
                }

                item { VerificationDocumentsSection() }

                item { RegisteredComplaintsSection() }

                item {
                    CustomSectionTitle(
                        icon = Icons.Default.Inbox,
                        title = "عروض بانتظار الموافقة"
                    )
                }
                items(uiState.offers) { offer -> PendingOfferCard(offer = offer) }

                item {
                    Row(
                        modifier = Modifier.height(IntrinsicSize.Min),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        FinancialSummaryCard(
                            earnings = uiState.totalEarnings,
                            modifier = Modifier.weight(1f)
                        )
                        PerformanceChartCard(
                            growth = uiState.earningsGrowth,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                item { LatestReviewsSection() }
                item { Spacer(modifier = Modifier.height(32.dp)) }
            }
        }
    }
}

@Composable
private fun CustomSectionTitle(
    icon: ImageVector,
    title: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Icon(icon, null, tint = PrimaryOrange, modifier = Modifier.size(20.dp))
        Spacer(Modifier.width(8.dp))
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = DarkNavy
        )
    }
}

@Composable
fun TechnicianInfoCard(state: ComplaintTechnicianUiState) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color(0xFFF1F5F9))
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.technician),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
                if (state.isOnline) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .offset(x = (-4).dp, y = 4.dp)
                            .background(Color(0xFF22C55E), CircleShape)
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "نشط",
                            color = Color.White,
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Spacer(Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(state.name, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp, color = DarkNavy)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Engineering, null, modifier = Modifier.size(14.dp), tint = Color.Gray)
                    Text(" ${state.specialty}", fontSize = 12.sp, color = Color.Gray)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.CalendarToday, null, modifier = Modifier.size(14.dp), tint = Color.Gray)
                    Text(" ${state.experience}", fontSize = 12.sp, color = Color.Gray)
                }
            }
        }
    }
}

@Composable
private fun StatSmallCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    unit: String = "",
    icon: ImageVector? = null,
    iconColor: Color = Color.Gray
) {
    Card(
        modifier = modifier.height(80.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFF1F5F9))
    ) {
        Column(modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(title, fontSize = 10.sp, color = Color(0xFF94A3B8))
            Spacer(Modifier.weight(1f))
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (icon != null) Icon(icon, null, modifier = Modifier.size(14.dp), tint = iconColor)
                Text(value, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = DarkNavy)
                if (unit.isNotEmpty()) Text(" $unit", fontSize = 10.sp, color = Color.Gray)
            }
        }
    }
}

@Composable
fun VerificationDocumentsSection() {
    Column {
        CustomSectionTitle(icon = Icons.Default.VerifiedUser, title = "وثائق التحقق")
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            DocumentItem(label = "رخصة القيادة", modifier = Modifier.weight(1f))
            DocumentItem(label = "بطاقة الهوية", modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun DocumentItem(label: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFF1F5F9))
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .background(Color(0xFFF8FAFC), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Image, null, tint = Color(0xFFCBD5E1), modifier = Modifier.size(32.dp))
            }
            Text(label, fontSize = 11.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(top = 8.dp))
            ComplaintActionButton(
                text = "عرض",
                onClick = {},
                modifier = Modifier
                    .padding(top = 4.dp)
                    .height(32.dp)
            )
        }
    }
}

@Composable
private fun RegisteredComplaintsSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFF1F5F9))
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    Modifier
                        .size(44.dp)
                        .background(Color(0xFFFEF2F2), RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Warning, null, tint = Color(0xFFEF4444), modifier = Modifier.size(24.dp))
                }
                Spacer(Modifier.width(12.dp))
                Column {
                    Text("إجمالي الشكاوى: ٥", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = DarkNavy)
                    Text("٢ مفتوحة | ٣ مغلقة", fontSize = 12.sp, color = Color.Gray)
                }
            }
            Icon(Icons.Default.ChevronLeft, null, tint = Color(0xFFCBD5E1))
        }
    }
}

@Composable
private fun FinancialSummaryCard(
    earnings: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFF1F5F9))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("الملخص المالي", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
            Text(
                earnings,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = DarkNavy,
                modifier = Modifier.padding(top = 4.dp)
            )
            Text("إجمالي الأرباح", fontSize = 10.sp, color = Color.LightGray)
            Box(Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .height(4.dp)
                .background(Color(0xFFF1F5F9), CircleShape)) {
                Box(Modifier
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight()
                    .background(PrimaryOrange, CircleShape))
            }
        }
    }
}

@Composable
private fun PerformanceChartCard(
    growth: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFF1F5F9))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("الأداء", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
            Row(
                Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                    .height(40.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                listOf(0.4f, 0.8f, 1f, 0.6f).forEach { h ->
                    Box(
                        Modifier
                            .weight(1f)
                            .fillMaxHeight(h)
                            .background(PrimaryOrange.copy(alpha = h), RoundedCornerShape(2.dp))
                    )
                }
            }
            Text(
                growth,
                fontSize = 10.sp,
                color = Color(0xFF16A34A),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
private fun PendingOfferCard(offer: TechnicianOffer) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(2.dp, Color(0xFFFFEDD5))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text(offer.title, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    Text(offer.timeAgo, fontSize = 10.sp, color = Color.Gray)
                }
                StatusBadge(label = "قيد المراجعة", color = Color(0xFFEA580C), backgroundColor = Color(0xFFFFEDD5))
            }
            Text(
                offer.description,
                fontSize = 11.sp,
                color = DarkNavy,
                modifier = Modifier.padding(vertical = 8.dp),
                lineHeight = 16.sp
            )
            DecisionActionRow(onConfirm = {}, onReject = {})
        }
    }
}

@Composable
private fun LatestReviewsSection() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("أحدث التقييمات", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = DarkNavy)
            Text(
                "عرض الكل",
                color = PrimaryOrange,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { })
        }
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFFF1F5F9))
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Row {
                        repeat(5) {
                            Icon(
                                Icons.Default.Star,
                                null,
                                tint = PrimaryOrange,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }
                    Text("محمد علي", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
                Text(
                    "فني محترف وسريع جداً، وصل في الوقت المحدد وحل المشكلة بسرعة.",
                    fontSize = 11.sp,
                    color = Color(0xFF64748B),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun DecisionActionRow(onConfirm: () -> Unit, onReject: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        ComplaintActionButton(
            text = "قبول",
            onClick = onConfirm,
            isPrimary = true,
            containerColor = Color(0xFF16A34A),
            modifier = Modifier
                .weight(1f)
                .height(36.dp)
        )
        ComplaintActionButton(
            text = "رفض",
            onClick = onReject,
            isPrimary = false,
            borderColor = Color(0xFFFECACA),
            contentColor = Color.Red,
            modifier = Modifier
                .weight(1f)
                .height(36.dp)
        )
    }
}

@Composable
private fun TechnicianActionBottomBar() {
    Surface(color = Color.White, shadowElevation = 8.dp, border = BorderStroke(1.dp, Color(0xFFE2E8F0))) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                ComplaintActionButton(
                    text = "حذف الحساب",
                    onClick = {},
                    icon = Icons.Default.Delete,
                    isPrimary = false,
                    contentColor = Color.Red,
                    borderColor = Color.Red,
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                )
                ComplaintActionButton(
                    text = "توجيه إنذار",
                    onClick = {},
                    icon = Icons.Default.Warning,
                    isPrimary = false,
                    contentColor = DarkNavy,
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                )
            }
            Spacer(Modifier.height(12.dp))
            ComplaintActionButton(
                text = "إيقاف الفني مؤقتاً",
                onClick = {},
                icon = Icons.Default.PauseCircle,
                isPrimary = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            )
        }
    }
}

@Preview(showBackground = true, heightDp = 1200)
@Composable
fun ComplainantTechnicianScreenPreview() {
    ComplainantTechnicianScreen(onBack = {})
}