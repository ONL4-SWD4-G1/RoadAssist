package com.example.app_admin.ui.technicians

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_admin.model.Technician
import com.example.app_admin.ui.customes.* // تأكدي من استيراد كل مكونات الـ customes
import com.example.app_admin.ui.theme.*

// 1. تعريف الـ Enum هنا خارج الـ Composable عشان يتشاف في الملف كله
enum class DetailFlow { VIEW, SELECT_REJECT_REASON, REJECT_SUCCESS, ACCEPT_SUCCESS }

@Composable
fun TechnicianDetailScreen(technician: Technician, onBack: () -> Unit) {
    var currentFlow by remember { mutableStateOf(DetailFlow.VIEW) }
    var rejectionReason by remember { mutableStateOf("") }

    when (currentFlow) {
        DetailFlow.VIEW -> {
            TechnicianInfoContent(
                technician = technician, onBack = onBack,
                onRejectClick = { currentFlow = DetailFlow.SELECT_REJECT_REASON },
                onAcceptClick = { currentFlow = DetailFlow.ACCEPT_SUCCESS }
            )
        }
        DetailFlow.SELECT_REJECT_REASON -> {
            RejectReasonSelectionScreen(
                technician = technician,
                onCancel = { currentFlow = DetailFlow.VIEW },
                onConfirmReject = { reason ->
                    rejectionReason = reason
                    currentFlow = DetailFlow.REJECT_SUCCESS
                }
            )
        }
        DetailFlow.REJECT_SUCCESS -> {
            ResultTemplate(
                imageRes = com.example.app_admin.R.drawable.reject,
                title = "تم رفض طلب التسجيل",
                subtitle = "تم إرسال سبب الرفض للفني وحفظ الطلب في السجل بنجاح.",
                technician = technician,
                isSuccess = false,
                rejectionReason = rejectionReason,
                buttonText = "العودة لقائمة الانتظار",
                secondaryButtonText = "عرض السجل التاريخي",
                onReturn = onBack
            )
        }
        DetailFlow.ACCEPT_SUCCESS -> {
            ResultTemplate(
                imageRes = com.example.app_admin.R.drawable.successicon,
                title = "تم قبول الفني بنجاح",
                subtitle = "تم إرسال إشعار القبول وتفعيل الحساب الآن للفني المعتمد.",
                technician = technician,
                isSuccess = true,
                buttonText = "العودة لطلبات التسجيل",
                secondaryButtonText = "عرض ملف الفني",
                onReturn = onBack
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TechnicianInfoContent(
    technician: Technician,
    onBack: () -> Unit,
    onRejectClick: () -> Unit,
    onAcceptClick: () -> Unit
) {
    var notes by remember { mutableStateOf("") }

    Scaffold(
        containerColor = BackgroundGray,
        topBar = {
            TopAppBar(
                title = {
                    Column(horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth().padding(end = 16.dp)) {
                        Text(technician.name, color = SoftWhite, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text("قيد التسجيل ●", color = PrimaryOrange, fontSize = 12.sp)
                    }
                },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.Default.ArrowForward, "رجوع", tint = SoftWhite) } },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = NavyBlue)
            )
        },
        bottomBar = {
            Surface(shadowElevation = 8.dp) {
                Row(modifier = Modifier.fillMaxWidth().background(SoftWhite).padding(16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    OutlinedButton(
                        onClick = onRejectClick, modifier = Modifier.weight(1f).height(50.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color.Red),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(Icons.Default.Close, null, Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text("رفض", fontWeight = FontWeight.Bold)
                    }
                    Button(
                        onClick = onAcceptClick, modifier = Modifier.weight(2f).height(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(Icons.Default.CheckCircle, null, Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text("قبول الطلب", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    ) { padding ->
        // تعديل الـ LazyColumn للتأكد من عدم وجود تضارب في الأنواع
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Box(Modifier.padding(vertical = 30.dp)) {
                    Box(Modifier.size(110.dp).clip(CircleShape).background(BorderGray).border(4.dp, SoftWhite, CircleShape), Alignment.Center) {
                        Icon(Icons.Default.Person, null, Modifier.size(60.dp), NavyBlue)
                    }
                    Box(Modifier.align(Alignment.BottomEnd).offset((-5).dp, (-5).dp).size(24.dp).clip(CircleShape).background(PrimaryOrange), Alignment.Center) {
                        Icon(Icons.Default.Verified, null, tint = SoftWhite, modifier = Modifier.size(16.dp))
                    }
                }
            }

            item {
                SectionCard("المعلومات الشخصية", Icons.Default.PersonOutline) {
                    Row(Modifier.fillMaxWidth()) {
                        InfoItem("الاسم الكامل", technician.name, Modifier.weight(1f))
                        InfoItem("رقم الهاتف", technician.phone, Modifier.weight(1f))
                    }
                    HorizontalDivider(Modifier.padding(vertical = 8.dp), thickness = 1.dp, color = DividerGray)
                    Row(Modifier.fillMaxWidth()) {
                        InfoItem("المدينة", technician.city, Modifier.weight(1f))
                        InfoItem("التخصص", technician.specialty, Modifier.weight(1f))
                    }
                    HorizontalDivider(Modifier.padding(vertical = 8.dp), thickness = 1.dp, color = DividerGray)
                    Row(Modifier.fillMaxWidth()) {
                        InfoItem("سنوات الخبرة", "${technician.experience} سنوات", Modifier.weight(1f))
                        InfoItem("ملكية سيارة", "نعم (يملك سيارة)", valueColor = SuccessGreen, modifier = Modifier.weight(1f))
                    }
                }
            }

            item {
                SectionCard("المستندات المرفوعة", Icons.Default.Description) {
                    DocumentRow("البطاقة الشخصية")
                    DocumentRow("رخصة القيادة")
                    DocumentRow("شهادات الخبرة", isFile = true)
                }
            }

            item {
                SectionCard("ملاحظات داخلية (الأدمن)", Icons.Default.EditNote) {
                    OutlinedTextField(
                        value = notes,
                        onValueChange = { notes = it },
                        placeholder = { Text("أضف ملاحظاتك...", fontSize = 13.sp) },
                        modifier = Modifier.fillMaxWidth().height(100.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = BackgroundGray,
                            unfocusedContainerColor = BackgroundGray,
                            focusedBorderColor = PrimaryOrange,
                            unfocusedBorderColor = BorderGray
                        )
                    )
                }
            }

            item {
                Spacer(Modifier.height(20.dp))
            }
        }
    }
}