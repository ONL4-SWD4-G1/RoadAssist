package com.example.app_admin.ui.customes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_admin.model.Technician
import com.example.app_admin.ui.theme.*

@Composable
fun ResultTemplate(
    imageRes: Int,
    title: String,
    subtitle: String,
    technician: Technician,
    isSuccess: Boolean,
    rejectionReason: String = "",
    buttonText: String,
    secondaryButtonText: String,
    onReturn: () -> Unit
) {
    Scaffold(
        containerColor = BackgroundGray,
        bottomBar = {
            Column(
                modifier = Modifier.fillMaxWidth().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = onReturn,
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = NavyBlue),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    if (!isSuccess) Icon(Icons.Default.List, null, modifier = Modifier.size(20.dp))
                    if (!isSuccess) Spacer(Modifier.width(8.dp))
                    Text(buttonText, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
                TextButton(onClick = { /* التنقل الإضافي */ }) {
                    Text(secondaryButtonText, color = DarkGray, fontSize = 14.sp)
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.size(140.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(title, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, color = NavyBlue)
            Text(
                subtitle,
                fontSize = 14.sp,
                color = DarkGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp, bottom = 32.dp),
                lineHeight = 20.sp
            )

            if (!isSuccess) {
                Text("ملخص الرفض", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End, color = DarkGray, fontSize = 13.sp)
                Spacer(Modifier.height(8.dp))
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = SoftWhite),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                        Column(horizontalAlignment = Alignment.End) {
                            Text("اسم الفني", fontSize = 11.sp, color = DarkGray)
                            Text(technician.name, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                            Text(technician.specialty, fontSize = 12.sp, color = NavyBlue)
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Box(modifier = Modifier.size(50.dp).clip(RoundedCornerShape(12.dp)).background(DividerGray)) {
                            Icon(Icons.Default.Person, null, modifier = Modifier.align(Alignment.Center), tint = NavyBlue)
                        }
                    }

                    HorizontalDivider(Modifier.padding(vertical = 12.dp), thickness = 0.5.dp, color = DividerGray)

                    if (isSuccess) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.CheckCircle, null, tint = SuccessGreen, modifier = Modifier.size(16.dp))
                                Spacer(Modifier.width(4.dp))
                                Text("تم التوثيق", color = SuccessGreen, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                            }
                            Text("تاريخ التفعيل: 24 مايو 2024", fontSize = 13.sp, color = DarkGray)
                        }
                    } else {
                        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {
                            Text(rejectionReason, color = Color.Red, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("سبب الرفض", fontSize = 13.sp, color = DarkGray)
                            Icon(Icons.Default.Info, null, tint = DarkGray, modifier = Modifier.size(16.dp).padding(start = 4.dp))
                        }
                    }
                }
            }

            if (!isSuccess) {
                Spacer(modifier = Modifier.height(16.dp))
                Box(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(Color(0xFFFFF7ED)).padding(12.dp)) {
                    Row(horizontalArrangement = Arrangement.End) {
                        Text(
                            "تم إخطار الفني عبر الرسائل النصية والبريد الإلكتروني بقرار اللجنة. يمكن للفني إعادة التقديم بعد تصحيح البيانات.",
                            modifier = Modifier.weight(1f), textAlign = TextAlign.End, fontSize = 11.sp, color = Color(0xFF9A3412)
                        )
                        Icon(Icons.Default.NotificationsActive, null, tint = PrimaryOrange, modifier = Modifier.size(18.dp).padding(start = 8.dp))
                    }
                }
            }
        }
    }
}