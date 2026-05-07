package com.example.app_admin.user.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app_admin.theme.NavyBlue
import com.example.app_admin.theme.PrimaryOrange
import com.example.app_admin.user.viewModel.UserSuspensionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserSuspensionScreen(
    viewModel: UserSuspensionViewModel = viewModel(),
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) onBack()
    }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold(
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            containerColor = Color(0xFFF8F7F6),
            bottomBar = {
                SuspensionBottomActions(
                    isLoading = uiState.isLoading,
                    onConfirm = { viewModel.confirmSuspension() },
                    onCancel = onBack
                )
            }
        ) { padding ->
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = padding.calculateBottomPadding()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        SuspensionHeader(
                            name = uiState.userName,
                            orders = uiState.totalOrders,
                            complaints = uiState.falseComplaints,
                            onBack = onBack
                        )
                    }

                    item {
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .offset(y = (-32).dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            SuspensionTypeCard(
                                isPermanent = uiState.isPermanent,
                                onTypeChange = { viewModel.togglePermanent(it) },
                                selectedDuration = uiState.selectedDuration,
                                onDurationChange = { viewModel.updateDuration(it) }
                            )

                            SuspensionReasonCard(
                                reason = uiState.suspensionReason,
                                comment = uiState.adminComment,
                                onCommentChange = { viewModel.updateComment(it) }
                            )

                            Text(
                                text = "معاينة الرسالة للمستخدم",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF64748B),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 4.dp),
                                textAlign = TextAlign.Start
                            )

                            UserMessagePreview(
                                name = uiState.userName.split(" ").firstOrNull() ?: "",
                                duration = if (uiState.isPermanent) "دائماً" else uiState.selectedDuration,
                                reason = uiState.suspensionReason
                            )

                            Spacer(modifier = Modifier.height(20.dp))
                        }
                    }
                }

                if (uiState.isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.1f)),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = PrimaryOrange)
                    }
                }
            }
        }
    }
}

@Composable
fun SuspensionHeader(name: String, orders: String, complaints: String, onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
            .background(NavyBlue)
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(top = 16.dp, bottom = 64.dp, start = 24.dp, end = 24.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Box(modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = onBack, modifier = Modifier.align(Alignment.CenterStart)) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, null, tint = Color.White)
                }
                Text(
                    "إيقاف حساب المستخدم",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White.copy(0.05f), RoundedCornerShape(16.dp))
                    .border(1.dp, Color.White.copy(0.1f), RoundedCornerShape(16.dp))
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .border(2.dp, PrimaryOrange, CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Person, null, modifier = Modifier.size(40.dp), tint = NavyBlue)
                }
                Spacer(Modifier.width(16.dp))
                Column(horizontalAlignment = Alignment.Start, modifier = Modifier.weight(1f)) {
                    Text(name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        HeaderStatItem("إجمالي الطلبات", orders, PrimaryOrange)
                        HeaderStatItem("شكاوى كاذبة", complaints, Color(0xFFF87171))
                    }
                }
            }
        }
    }
}

@Composable
fun HeaderStatItem(label: String, value: String, valueColor: Color) {
    Column(horizontalAlignment = Alignment.Start) {
        Text(value, color = valueColor, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Text(label, color = Color.White.copy(0.8f), fontSize = 11.sp)
    }
}

@Composable
fun SuspensionTypeCard(
    isPermanent: Boolean,
    onTypeChange: (Boolean) -> Unit,
    selectedDuration: String,
    onDurationChange: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color(0xFFF1F5F9))
    ) {
        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Timer, null, tint = PrimaryOrange, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
                Text("نوع الإيقاف", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = NavyBlue)
            }

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                SuspensionToggleItem("دائم", isPermanent, Modifier.weight(1f)) { onTypeChange(true) }
                SuspensionToggleItem("مؤقت", !isPermanent, Modifier.weight(1f)) { onTypeChange(false) }
            }

            if (!isPermanent) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    val durations = listOf("٣ أيام", "٧ أيام", "٣٠ يوم", "مخصص")
                    durations.forEach { duration ->
                        QuickSelectButton(
                            text = duration,
                            isSelected = selectedDuration == duration,
                            modifier = Modifier.weight(1f),
                            onClick = { onDurationChange(duration) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SuspensionReasonCard(reason: String, comment: String, onCommentChange: (String) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color(0xFFF1F5F9))
    ) {
        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.WarningAmber, null, tint = PrimaryOrange, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
                Text("سبب الإيقاف", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = NavyBlue)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF8FAFC), RoundedCornerShape(12.dp))
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(12.dp))
                    .padding(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(reason, modifier = Modifier.weight(1f), textAlign = TextAlign.Start, fontSize = 14.sp)
                    Icon(Icons.Default.KeyboardArrowDown, null, tint = Color(0xFF6B7280))
                }
            }

            OutlinedTextField(
                value = comment,
                onValueChange = onCommentChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                placeholder = {
                    Text(
                        "أضف تعليقاً إدارياً (اختياري)...",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start
                    )
                },
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFF8FAFC),
                    unfocusedBorderColor = Color(0xFFE2E8F0)
                )
            )
        }
    }
}

@Composable
fun UserMessagePreview(name: String, duration: String, reason: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .background(PrimaryOrange.copy(0.2f), RoundedCornerShape(4.dp))
                        .padding(4.dp)
                ) {
                    Icon(Icons.Default.NotificationsActive, null, modifier = Modifier.size(16.dp), tint = PrimaryOrange)
                }
                Spacer(Modifier.width(8.dp))
                Text("نظام استغاثة", fontSize = 10.sp, fontWeight = FontWeight.Bold)
            }
            Text(
                text = "عزيزي $name، نود إبلاغك بأنه قد تم إيقاف حسابك مؤقتاً لمدة $duration بسبب ($reason). يمكنك التواصل مع الدعم الفني لمزيد من التفاصيل.",
                fontSize = 12.sp, color = Color(0xFF334155), textAlign = TextAlign.Start, lineHeight = 19.sp
            )
        }
    }
}

@Composable
fun SuspensionBottomActions(isLoading: Boolean, onConfirm: () -> Unit, onCancel: () -> Unit) {
    Surface(color = Color.White, shadowElevation = 8.dp, border = BorderStroke(1.dp, Color(0xFFE2E8F0))) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .padding(bottom = 16.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = onConfirm,
                enabled = !isLoading,
                modifier = Modifier
                    .weight(2f)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDC2626)),
                shape = RoundedCornerShape(16.dp)
            ) {
                if (isLoading) CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White)
                else Text("تأكيد الإيقاف", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            Button(
                onClick = onCancel,
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF1F5F9)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("إلغاء", color = Color(0xFF475569), fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun SuspensionToggleItem(text: String, isSelected: Boolean, modifier: Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(if (isSelected) Color(0xFFFFF7ED) else Color(0xFFF8FAFC))
            .border(2.dp, if (isSelected) PrimaryOrange else Color(0xFFF1F5F9), RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text, color = if (isSelected) PrimaryOrange else NavyBlue, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }
}

@Composable
fun QuickSelectButton(text: String, isSelected: Boolean, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .background(if (isSelected) Color(0xFFFFF7ED) else Color.Transparent, RoundedCornerShape(8.dp))
            .border(1.dp, if (isSelected) PrimaryOrange else Color(0xFFE2E8F0), RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text,
            fontSize = 12.sp,
            color = if (isSelected) PrimaryOrange else Color(0xFF64748B),
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun UserSuspensionScreenPreview() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold(
            containerColor = Color(0xFFF8F7F6),
            bottomBar = {
                SuspensionBottomActions(
                    isLoading = false,
                    onConfirm = {},
                    onCancel = {}
                )
            }
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    SuspensionHeader(
                        name = "أحمد محمد علي",
                        orders = "150",
                        complaints = "2",
                        onBack = {}
                    )
                }
                item {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .offset(y = (-32).dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        SuspensionTypeCard(
                            isPermanent = false,
                            onTypeChange = {},
                            selectedDuration = "٧ أيام",
                            onDurationChange = {}
                        )
                        SuspensionReasonCard(
                            reason = "إلغاء الطلبات المتكرر",
                            comment = "",
                            onCommentChange = {}
                        )
                        Text(
                            text = "معاينة الرسالة للمستخدم",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF64748B),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 4.dp),
                            textAlign = TextAlign.Start
                        )
                        UserMessagePreview(
                            name = "أحمد",
                            duration = "٧ أيام",
                            reason = "إلغاء الطلبات المتكرر"
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
        }
    }
}