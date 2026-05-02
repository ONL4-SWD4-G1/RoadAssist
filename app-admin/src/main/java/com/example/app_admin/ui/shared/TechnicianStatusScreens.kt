package com.example.app_admin.ui.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_admin.model.Technician
import com.example.app_admin.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RejectReasonSelectionScreen(
    technician: Technician,
    onCancel: () -> Unit,
    onConfirmReject: (String) -> Unit
) {
    val reasons = listOf("صورة البطاقة غير واضحة",
        "مستندات ناقصة", "رخصة القيادة منتهية", "بيانات غير صحيحة", "أخرى")
    var selectedReason by remember { mutableStateOf(reasons[0]) }
    var additionalNotes by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("رفض طلب تسجيل الفني",
                    color = SoftWhite,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold) },
                navigationIcon = { IconButton(onClick = onCancel)
                { Icon(Icons.Default.ArrowForward,
                    contentDescription = null, tint = SoftWhite) } },
                colors = TopAppBarDefaults
                    .topAppBarColors(containerColor = NavyBlue)
            )
        },

        bottomBar = {
            Column(modifier = Modifier.padding(16.dp)) {
                Button(
                    onClick = { onConfirmReject(selectedReason) },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("إرسال ورفض الطلب", fontWeight = FontWeight.Bold)
                }
                TextButton(onClick = onCancel, modifier = Modifier.fillMaxWidth()) {
                    Text("إلغاء", color = DarkGray)
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(BackgroundGray)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = SoftWhite),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.End) {
                        Text(technician.name, fontWeight = FontWeight.Bold,
                            fontSize = 16.sp)
                        Text("${technician.specialty} • ${technician.city}",
                            fontSize = 12.sp, color = DarkGray)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Box(modifier = Modifier.size(50.dp).clip(CircleShape)
                        .background(DividerGray),
                        contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Person,
                            contentDescription = null, tint = NavyBlue)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("حدد سبب الرفض", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = PrimaryOrange
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Card(
                modifier = Modifier.fillMaxWidth().border(1.dp,
                    BorderGray, RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = SoftWhite),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column {
                    reasons.forEachIndexed { index, reason ->
                        Row(
                            Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End
                        ) {
                            Text(reason, fontSize = 14.sp, color = NavyBlue)
                            RadioButton(
                                selected = selectedReason == reason,
                                onClick = { selectedReason = reason },
                                colors = RadioButtonDefaults.colors(selectedColor = PrimaryOrange)
                            )
                        }
                        if (index < reasons.size - 1) {
                            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp, color = DividerGray)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text("ملاحظات إضافية (اختياري)", fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End)
            Spacer(modifier = Modifier.height(8.dp))


            OutlinedTextField(
                value = additionalNotes,
                onValueChange = { additionalNotes = it },
                placeholder = { Text("اكتب ملاحظات إضافية لتوضيح سبب الرفض للفني...", fontSize = 12.sp, textAlign = TextAlign.End, modifier = Modifier.fillMaxWidth()) },
                modifier = Modifier.fillMaxWidth().height(120.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = SoftWhite,
                    unfocusedContainerColor = SoftWhite,
                    focusedBorderColor = PrimaryOrange,
                    unfocusedBorderColor = BorderGray
                )
            )

            Spacer(modifier = Modifier.height(20.dp))


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFFFF7ED))
                    .border(1.dp, Color(0xFFFFEDD5),
                        RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                Column(horizontalAlignment = Alignment.End) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("معاينة الرسالة الصادرة للفني", color = PrimaryOrange, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(Icons.Default.Visibility,
                            contentDescription = null, tint = PrimaryOrange, modifier = Modifier.size(16.dp))
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "" +
                                "مرحباً ${technician.name}، تم مراجع" +
                                "ة طلبك للانضمام إلى منصة استغاثة،" +
                                " وللأسف تم رفض طلبك للسبب التالي: ($selectedReason). يرجى تحديث بياناتك وإعادة" +
                                " المحاولة مرة أخرى.",
                        fontSize = 12.sp,
                        color = Color(0xFF9A3412),
                        textAlign = TextAlign.End,
                        lineHeight = 18.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}