// ui/complaints/ComplaintsScreen.kt
package com.example.app_admin.ui.complaints

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Complaint(
    val id: Int,
    val clientName: String,
    val technicianName: String,
    val issueType: String,
    val status: ComplaintStatus,
    val timeAgo: String
)

enum class ComplaintStatus { OPEN, UNDER_REVIEW, RESOLVED }

val sampleComplaints = listOf(
    Complaint(1, "سارة أحمد", "أحمد محمد", "بطارية / كهرباء", ComplaintStatus.OPEN, "منذ ساعتين"),
    Complaint(2, "محمد القحطاني", "ياسين عبدالله", "نظام المحرك", ComplaintStatus.UNDER_REVIEW, "منذ 5 ساعات"),
    Complaint(3, "عبدالله منصور", "فهد العتيبي", "إطارات / بنشر", ComplaintStatus.RESOLVED, "أمس")
)

@Composable
fun ComplaintsScreen(onHandleComplaint: (Complaint) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(sampleComplaints) { complaint ->
            ComplaintCard(
                complaint = complaint,
                onHandleClick = { onHandleComplaint(complaint) }
            )
        }
    }
}

@Composable
fun ComplaintCard(complaint: Complaint, onHandleClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        border = BorderStroke(1.dp, color = Color(0xffF1F5F9)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // الاسم على اليمين والـ badge على اليسار
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // badge على اليسار
                ComplaintStatusBadge(status = complaint.status)

                // الاسم على اليمين
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = complaint.clientName,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        textAlign = TextAlign.End
                    )
                    Text(
                        text = "العميل",
                        fontSize = 11.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.End
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // الفني المسؤول ونوع العطل - على اليمين
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                // نوع العطل
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.End
                ) {
                    Text("نوع العطل", fontSize = 11.sp, color = Color.Gray, textAlign = TextAlign.End)
                    Text(complaint.issueType, fontSize = 13.sp, fontWeight = FontWeight.Medium, textAlign = TextAlign.End)
                }

                Spacer(modifier = Modifier.width(16.dp))

                // الفني المسؤول
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.End
                ) {
                    Text("الفني المسؤول", fontSize = 11.sp, color = Color.Gray, textAlign = TextAlign.End)
                    Text(complaint.technicianName, fontSize = 13.sp, fontWeight = FontWeight.Medium, textAlign = TextAlign.End)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // التاريخ على اليمين
            Text(
                text = "تاريخ الشكوى: ${complaint.timeAgo}",
                fontSize = 11.sp,
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )

            Spacer(modifier = Modifier.height(12.dp))

            // خط فاصل
            HorizontalDivider(
                color = Color(0xFFE2E8F0),
                thickness = 1.dp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // الزرار
            if (complaint.status == ComplaintStatus.RESOLVED) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Visibility,
                            contentDescription = "",
                            tint = Color(0xFF94A3B8)
                        )
                    }
                    Button(
                        onClick = onHandleClick,
                        modifier = Modifier
                            .weight(1f)
                            .height(36.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF1F5F9)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("مكتملة", color = Color(0xff94A3B8))
                    }
                }
            } else {
                Button(
                    onClick = onHandleClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(36.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E293B)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("معالجة الشكوى", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun ComplaintStatusBadge(status: ComplaintStatus) {
    val (text, color) = when (status) {
        ComplaintStatus.OPEN -> "مفتوحة" to Color(0xFFC2410C)
        ComplaintStatus.UNDER_REVIEW -> "قيد المراجعة" to Color(0xFF1D4ED8)
        ComplaintStatus.RESOLVED -> "تم الحل" to Color(0xFF15803D)
    }
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(color.copy(alpha = 0.15f))
            .padding(horizontal = 8.dp, vertical = 3.dp)
    ) {
        Text(text, color = color, fontSize = 11.sp, fontWeight = FontWeight.Medium)
    }
}

@Preview(showBackground = true)
@Composable
fun ComplaintsScreenPreview() {
    ComplaintsScreen(onHandleComplaint = {})
}