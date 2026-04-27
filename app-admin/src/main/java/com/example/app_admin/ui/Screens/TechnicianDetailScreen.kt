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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_admin.model.Technician


val NavyBlue = Color(0xFF1E293B)
val PrimaryOrange = Color(0xFFEC9513)
val DarkGray = Color(0xFF6B7280)
val SuccessGreen = Color(0xFF16A34A)
val LightBlueGray = Color(0xFF94A3B8)
val BackgroundGray = Color(0xFFF8FAFC)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TechnicianDetailScreen(
    technician: Technician,
    onBack: () -> Unit
) {
    var notes by remember { mutableStateOf("") }

    Scaffold(
        containerColor = BackgroundGray,
        topBar = {
            TopAppBar(
                title = {
                    Column(horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth().padding(end = 16.dp)) {
                        Text(technician.name, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text("قيد التسجيل ●", color = PrimaryOrange, fontSize = 12.sp)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowForward, contentDescription = "رجوع", tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = { /* More options */ }) {
                        Icon(Icons.Default.MoreVert, contentDescription = null, tint = Color.White)
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
                        .background(Color.White)
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = { /* Action Reject */ },
                        modifier = Modifier.weight(1f).height(50.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color.Red),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(Icons.Default.Close, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("رفض", fontWeight = FontWeight.Bold)
                    }
                    Button(
                        onClick = { /* Action Accept */ },
                        modifier = Modifier.weight(2f).height(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(Icons.Default.CheckCircle, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("قبول الطلب", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // البروفايل
            item {
                Box(modifier = Modifier.padding(vertical = 30.dp)) {
                    Box(
                        modifier = Modifier
                            .size(110.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFE2E8F0))
                            .border(4.dp, Color.White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(60.dp), tint = NavyBlue)
                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .offset(x = (-5).dp, y = (-5).dp)
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(PrimaryOrange),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Verified, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                    }
                }
            }

            // المعلومات الشخصية
            item {
                SectionCard(title = "المعلومات الشخصية", icon = Icons.Default.PersonOutline) {
                    Row(Modifier.fillMaxWidth()) {
                        InfoItem(label = "الاسم الكامل", value = technician.name, modifier = Modifier.weight(1f))
                        InfoItem(label = "رقم الهاتف", value = technician.phone, modifier = Modifier.weight(1f))
                    }
                    Divider(Modifier.padding(vertical = 8.dp), color = Color(0xFFF1F5F9))
                    Row(Modifier.fillMaxWidth()) {
                        InfoItem(label = "المدينة", value = technician.city, modifier = Modifier.weight(1f))
                        InfoItem(label = "التخصص", value = technician.specialty, modifier = Modifier.weight(1f))
                    }
                    Divider(Modifier.padding(vertical = 8.dp), color = Color(0xFFF1F5F9))
                    Row(Modifier.fillMaxWidth()) {
                        InfoItem(label = "سنوات الخبرة", value = "${technician.experience} سنوات", modifier = Modifier.weight(1f))
                        InfoItem(label = "ملكية سيارة", value = "نعم (يملك سيارة)", valueColor = SuccessGreen, modifier = Modifier.weight(1f))
                    }
                }
            }

            // المستندات
            item {
                SectionCard(title = "المستندات المرفوعة", icon = Icons.Default.Description) {
                    DocumentRow(title = "البطاقة الشخصية")
                    DocumentRow(title = "رخصة القيادة")
                    DocumentRow(title = "شهادات الخبرة", isFile = true)
                }
            }

            // الملاحظات
            item {
                SectionCard(title = "ملاحظات داخلية (الأدمن)", icon = Icons.Default.EditNote) {
                    OutlinedTextField(
                        value = notes,
                        onValueChange = { notes = it },
                        placeholder = { Text("أضف ملاحظاتك حول هذا الطلب هنا...", fontSize = 13.sp) },
                        modifier = Modifier.fillMaxWidth().height(100.dp),
                        shape = RoundedCornerShape(12.dp),
                        // التعديل هنا: استخدام OutlinedTextFieldDefaults.colors
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFF8FAFC),
                            unfocusedContainerColor = Color(0xFFF8FAFC),
                            focusedBorderColor = PrimaryOrange,
                            unfocusedBorderColor = Color(0xFFE2E8F0)
                        )
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}

@Composable
fun SectionCard(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector, content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = NavyBlue)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(icon, contentDescription = null, tint = NavyBlue, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            content()
        }
    }
}

@Composable
fun InfoItem(label: String, value: String, modifier: Modifier = Modifier, valueColor: Color = NavyBlue) {
    Column(modifier = modifier, horizontalAlignment = Alignment.End) {
        Text(label, fontSize = 12.sp, color = LightBlueGray)
        Text(value, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = valueColor, textAlign = TextAlign.End)
    }
}

@Composable
fun DocumentRow(title: String, isFile: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(1.dp, Color(0xFFF1F5F9), RoundedCornerShape(12.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Icon(Icons.Default.KeyboardArrowLeft, contentDescription = null, tint = LightBlueGray)
        Spacer(modifier = Modifier.weight(1f))
        Column(horizontalAlignment = Alignment.End) {
            Text(title, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = NavyBlue)
            Text("تم الرفع: ٢٠ أكتوبر ٢٠٢٣", fontSize = 11.sp, color = DarkGray)
        }
        Spacer(modifier = Modifier.width(12.dp))
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(if (isFile) Color(0xFFCBD5E1) else Color(0xFF475569)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                if (isFile) Icons.Default.Description else Icons.Default.Search,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}