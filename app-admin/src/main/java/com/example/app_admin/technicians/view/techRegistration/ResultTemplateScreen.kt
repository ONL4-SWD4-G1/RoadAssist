package com.example.app_admin.technicians.view.techRegistration

import android.R.drawable.ic_dialog_alert
import android.R.drawable.ic_dialog_info
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_admin.sampleTechnicians
import com.example.app_admin.technicians.model.Technician
import com.example.app_admin.theme.BackgroundGray
import com.example.app_admin.theme.DarkGray
import com.example.app_admin.theme.DividerGray
import com.example.app_admin.theme.NavyBlue
import com.example.app_admin.theme.PrimaryOrange
import com.example.app_admin.theme.SoftWhite
import com.example.app_admin.theme.SuccessGreen

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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = onReturn,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = NavyBlue),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    if (!isSuccess) Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    if (!isSuccess) Spacer(Modifier.width(8.dp))
                    Text(buttonText, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
                TextButton(onClick = { }) {
                    Text(secondaryButtonText, color = DarkGray, fontSize = 14.sp)
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.size(140.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = title,
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = NavyBlue
            )
            Text(
                text = subtitle,
                fontSize = 14.sp,
                color = DarkGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(
                    top = 8.dp,
                    bottom = 32.dp
                ),
                lineHeight = 20.sp
            )

            if (!isSuccess) {
                Text(
                    "ملخص الرفض", modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End, color = DarkGray, fontSize = 13.sp
                )
                Spacer(Modifier.height(8.dp))
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = SoftWhite),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(horizontalAlignment = Alignment.End) {
                            Text("اسم الفني", fontSize = 11.sp, color = DarkGray)
                            Text(
                                text = technician.name, fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                            Text(
                                text = technician.specialty, fontSize = 12.sp,
                                color = NavyBlue
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(DividerGray)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                modifier = Modifier.align(Alignment.Center),
                                tint = NavyBlue
                            )
                        }
                    }

                    HorizontalDivider(
                        Modifier.padding(vertical = 12.dp),
                        thickness = 0.5.dp, color = DividerGray
                    )

                    if (isSuccess) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = SuccessGreen,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(Modifier.width(4.dp))
                                Text(
                                    text = "تم التوثيق",
                                    color = SuccessGreen,
                                    fontSize = 13.sp, fontWeight = FontWeight.Bold
                                )
                            }
                            Text("تاريخ التفعيل: 24 مايو 2024", fontSize = 13.sp, color = DarkGray)
                        }
                    } else {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End
                        ) {
                            Text(
                                text = rejectionReason,
                                color = Color.Red,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "سبب الرفض",
                                fontSize = 13.sp,
                                color = DarkGray
                            )
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "",
                                tint = DarkGray,
                                modifier = Modifier
                                    .size(16.dp)
                                    .padding(start = 4.dp)
                            )
                        }
                    }
                }
            }

            if (!isSuccess) {
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFFFF7ED))
                        .padding(12.dp)
                ) {
                    Row(horizontalArrangement = Arrangement.End) {
                        Text(
                            text = "تم إخطار الفني عبر الرسائل" +
                                    " النصية والبريد الإلكتروني بقرار اللجنة. يمكن ل" +
                                    "لفني إعادة التقديم بعد تصحيح البيانات.",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.End,
                            fontSize = 11.sp,
                            color = Color(0xFF9A3412)
                        )
                        Icon(
                            imageVector = Icons.Default.NotificationsActive,
                            contentDescription = null,
                            tint = PrimaryOrange,
                            modifier = Modifier
                                .size(18.dp)
                                .padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Success State")
@Composable
fun PreviewResultTemplateSuccess() {
    ResultTemplate(
        imageRes = ic_dialog_info,
        title = "تم توثيق الحساب بنجاح",
        subtitle = "تمت الموافقة على طلب انضمام الفني للمنصة وتفعيل حسابه للعمل",
        technician = sampleTechnicians[0],
        isSuccess = true,
        buttonText = "العودة للرئيسية",
        secondaryButtonText = "عرض الملف الشخصي",
        onReturn = {}
    )
}

@Preview(showBackground = true, name = "Failure State")
@Composable
fun PreviewResultTemplateFailure() {
    ResultTemplate(
        imageRes = ic_dialog_alert,
        title = "تم رفض طلب التوثيق",
        subtitle = "للأسف لم يتم قبول طلب انضمام الفني بناءً على مراجعة البيانات المرفقة",
        technician = sampleTechnicians[0],
        isSuccess = false,
        rejectionReason = "المستندات غير واضحة",
        buttonText = "العودة لطلبات التوثيق",
        secondaryButtonText = "تواصل مع الفني",
        onReturn = {}
    )
}