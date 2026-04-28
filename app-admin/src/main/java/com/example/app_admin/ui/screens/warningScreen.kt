package com.example.app_admin.ui.screens

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
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_admin.ui.theme.BackgroundGray
import com.example.app_admin.ui.theme.BorderGray
import com.example.app_admin.ui.theme.DangerRed
import com.example.app_admin.ui.theme.LightBlueGray
import com.example.app_admin.ui.theme.LightText
import com.example.app_admin.ui.theme.NavyBlue
import com.example.app_admin.ui.theme.PrimaryOrange
import com.example.app_admin.ui.theme.SoftWhite

@Composable
fun WarningScreen(onBack: () -> Unit) {
    var warningReason by remember { mutableStateOf("") }
    var selectedTemplate by remember { mutableStateOf("") }
    var warningMessage by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .background(SoftWhite)
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = { },
                    modifier = Modifier
                        .weight(2f)
                        .height(50.dp),
                    colors = ButtonDefaults.
                    buttonColors(containerColor = PrimaryOrange),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.AutoMirrored.
                    Filled.Send, null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("إرسال التحذير الآن",
                        fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
                OutlinedButton(
                    onClick = onBack,
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.
                    foundation.BorderStroke(1.dp, BorderGray)
                ) {
                    Text("إلغاء", color = LightText)
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(BackgroundGray),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                WarningHeaderSection(onBack)
            }

            item {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .offset(y = (-30).dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = SoftWhite),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.End,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {

                            WarningInputLabel("سبب التحذير")
                            OutlinedTextField(
                                value = warningReason,
                                onValueChange = { warningReason = it },
                                modifier = Modifier.fillMaxWidth(),
                                placeholder = { Text("مثال: سلوك غير لائق، تأخير متكرر...",
                                    fontSize = 13.sp) },
                                shape = RoundedCornerShape(12.dp),
                                trailingIcon = { Icon(Icons.Default.ErrorOutline,
                                    "", tint = LightBlueGray) },
                                colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = BorderGray)
                            )

                            WarningInputLabel("قوالب جاهزة")
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Button(
                                    onClick = { /* عمل Logic للاختيار */ },
                                    shape = RoundedCornerShape(8.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = NavyBlue),
                                    modifier = Modifier.height(50.dp)
                                ) {
                                    Text("استخدام", fontSize = 12.sp)
                                }
                                OutlinedTextField(
                                    value = selectedTemplate,
                                    onValueChange = { selectedTemplate = it },
                                    modifier = Modifier.weight(1f),
                                    placeholder = { Text("اختر من القوالب...", fontSize = 13.sp) },
                                    shape = RoundedCornerShape(12.dp),
                                    trailingIcon = { Icon(Icons.Default.KeyboardArrowDown, null) },
                                    colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = BorderGray)
                                )
                            }

                            // نص الرسالة
                            WarningInputLabel("نص رسالة التحذير")
                            OutlinedTextField(
                                value = warningMessage,
                                onValueChange = { warningMessage = it },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(120.dp),
                                placeholder = { Text("اكتب تفاصيل التحذير هنا ليرا" +
                                        "ها المستخدم...", fontSize = 13.sp) },
                                shape = RoundedCornerShape(12.dp),
                                colors = OutlinedTextFieldDefaults
                                    .colors(unfocusedBorderColor = BorderGray)
                            )
                        }
                    }


                    Text(
                        "معاينة الرسالة النهائية",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = NavyBlue,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End
                    )

                    Surface(
                        color = Color(0xFFFFF7ED),
                        shape = RoundedCornerShape(12.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFFEDD5))
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.End) {
                                Text("تنبيه من الإدارة", color = PrimaryOrange,
                                    fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    "عزيزي عبدالرحمن، نلاحظ وجود عدد ك" +
                                            "بير من الشكاوي المتعلقة بطلباتك الأخيرة." +
                                            " نود تذكيرك بضرورة الالتزام بسياسات المنصة" +
                                            " لتجنب تعليق حسابك.",
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.End,
                                    color = Color(0xFF7C2D12),
                                    lineHeight = 18.sp
                                )
                            }
                            Spacer(Modifier.width(12.dp))
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(
                                        PrimaryOrange.copy(0.1f), CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.
                                NotificationsActive, null, tint = PrimaryOrange, modifier = Modifier.size(20.dp))
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
fun WarningHeaderSection(onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(NavyBlue)
    ) {
        // زر الرجوع
        IconButton(
            onClick = onBack,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopEnd)
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, null, tint = SoftWhite)
        }

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("إرسال تحذير", color = SoftWhite, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(horizontalAlignment = Alignment.End) {
                    Text("عبدالرحمن القحطاني", color = SoftWhite, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text("٣ شكاوي نشطة ● نسبة الاسترداد: ١٥٪", color = DangerRed.copy(0.9f), fontSize = 11.sp)
                }
                Spacer(modifier = Modifier.width(12.dp))
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(PrimaryOrange.copy(alpha = 0.2f))
                        .border(2.dp, PrimaryOrange, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Person, null, modifier = Modifier.size(35.dp), tint = SoftWhite)
                }
            }
        }
    }
}

@Composable
fun WarningInputLabel(label: String) {
    Text(
        text = label,
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold,
        color = NavyBlue,
        modifier = Modifier.padding(bottom = 4.dp)
    )
}