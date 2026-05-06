package com.example.app_admin.technicians.view

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_admin.R
import com.example.app_admin.shared.DocumentRow
import com.example.app_admin.shared.InfoItem
import com.example.app_admin.shared.RejectReasonSelectionScreen
import com.example.app_admin.shared.ResultTemplate
import com.example.app_admin.shared.SectionCard
import com.example.app_admin.technicians.model.DetailFlow
import com.example.app_admin.technicians.model.Technician
import com.example.app_admin.theme.BackgroundGray
import com.example.app_admin.theme.BorderGray
import com.example.app_admin.theme.DividerGray
import com.example.app_admin.theme.NavyBlue
import com.example.app_admin.theme.PrimaryOrange
import com.example.app_admin.theme.SoftWhite
import com.example.app_admin.theme.SuccessGreen

@Composable
fun TechnicianDetailScreen(technician: Technician, onBack: () -> Unit) {
    var currentFlow by remember { mutableStateOf(DetailFlow.VIEW) }
    var rejectionReason by remember { mutableStateOf("") }

    when (currentFlow) {
        DetailFlow.VIEW -> {
            TechnicianInfoContent(
                technician = technician,
                onBack = onBack,
                onRejectClick = { currentFlow = DetailFlow.SELECT_REJECT_REASON },
                onAcceptClick = { currentFlow = DetailFlow.ACCEPT_SUCCESS }
            )
        }

        DetailFlow.SELECT_REJECT_REASON -> {
            RejectReasonSelectionScreen(
                technician = technician,
                onCancel = { currentFlow = DetailFlow.VIEW },
                onConfirmReject = { reason: String ->
                    rejectionReason = reason
                    currentFlow = DetailFlow.REJECT_SUCCESS
                }
            )
        }

        DetailFlow.REJECT_SUCCESS -> {
            ResultTemplate(
                imageRes = R.drawable.reject,
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
                imageRes = R.drawable.successicon,
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
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 16.dp)
                    ) {
                        Text(technician.name, color = SoftWhite, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text("قيد التسجيل ●", color = PrimaryOrange, fontSize = 12.sp)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowForward, "رجوع", tint = SoftWhite)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = NavyBlue)
            )
        },
        bottomBar = {
            Surface(shadowElevation = 8.dp) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(SoftWhite)
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = onRejectClick,
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red),
                        border = BorderStroke(1.dp, Color.Red),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(Icons.Default.Close, null, Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text("رفض", fontWeight = FontWeight.Bold)
                    }
                    Button(
                        onClick = onAcceptClick,
                        modifier = Modifier
                            .weight(2f)
                            .height(50.dp),
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Box(Modifier.padding(vertical = 30.dp)) {
                    Box(
                        Modifier
                            .size(110.dp)
                            .clip(CircleShape)
                            .background(BorderGray)
                            .border(4.dp, SoftWhite, CircleShape),
                        Alignment.Center
                    ) {
                        Icon(Icons.Default.Person, null, Modifier.size(60.dp), NavyBlue)
                    }
                    Box(
                        Modifier
                            .align(Alignment.BottomEnd)
                            .offset((-5).dp, (-5).dp)
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(PrimaryOrange),
                        Alignment.Center
                    ) {
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
                        InfoItem(
                            "ملكية سيارة",
                            "نعم (يملك سيارة)",
                            valueColor = SuccessGreen,
                            modifier = Modifier.weight(1f)
                        )
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
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

            item { Spacer(Modifier.height(20.dp)) }
        }
    }
}