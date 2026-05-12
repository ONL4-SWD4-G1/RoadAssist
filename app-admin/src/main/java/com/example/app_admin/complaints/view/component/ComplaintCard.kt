package com.example.app_admin.complaints.view.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_admin.complaints.model.Complaint
import com.example.app_admin.complaints.model.ComplaintStatus
import com.example.app_admin.shared.StatusBadge

@Composable
fun ComplaintCard(
    complaint: Complaint,
    onHandleClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        border = BorderStroke(1.dp, color = Color(0xffF1F5F9)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                StatusBadge(
                    label = complaint.status.label,
                    color = complaint.status.color
                )

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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.End
                ) {
                    Text("نوع العطل", fontSize = 11.sp, color = Color.Gray, textAlign = TextAlign.End)
                    Text(complaint.issueType, fontSize = 13.sp, fontWeight = FontWeight.Medium, textAlign = TextAlign.End)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.End
                ) {
                    Text("الفني المسؤول", fontSize = 11.sp, color = Color.Gray,
                        textAlign = TextAlign.End)
                    Text(complaint.technicianName,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.End)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "تاريخ الشكوى: ${complaint.timeAgo}",
                fontSize = 11.sp,
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )

            Spacer(modifier = Modifier.height(12.dp))

            HorizontalDivider(
                color = Color(0xFFE2E8F0),
                thickness = 1.dp
            )

            Spacer(modifier = Modifier.height(12.dp))

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
                        colors = ButtonDefaults.
                        buttonColors(containerColor = Color(0xFFF1F5F9)),
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
                    colors = ButtonDefaults.
                    buttonColors(containerColor = Color(0xFF1E293B)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("معالجة الشكوى", color = Color.White)
                }
            }
        }
    }
}


@Preview(name = "Pending Complaint", showBackground = true, locale = "ar")
@Composable
fun PreviewComplaintCardPending() {
    val mockComplaint = Complaint(
        id = 1,
        clientName = "أحمد محمد المهندس",
        issueType = "تعطل المحرك المفاجئ",
        technicianName = "خالد العتيبي",
        status = ComplaintStatus.UNDER_REVIEW,
        timeAgo = "منذ ساعتين"
    )

    Box(modifier = Modifier.padding(8.dp)) {
        ComplaintCard(
            complaint = mockComplaint,
            onHandleClick = { }
        )
    }
}

@Preview(name = "Open Complaint", showBackground = true, locale = "ar")
@Composable
fun PreviewComplaintCardOpen() {
    val mockComplaint = Complaint(
        id = 3,
        clientName = "محمد العلي",
        issueType = "تأخير في الموعد",
        technicianName = "غير معين",
        status = ComplaintStatus.OPEN,
        timeAgo = "الآن"
    )

    Box(modifier = Modifier.padding(8.dp)) {
        ComplaintCard(
            complaint = mockComplaint,
            onHandleClick = { }
        )
    }
}

@Preview(name = "Resolved Complaint", showBackground = true, locale = "ar")
@Composable
fun PreviewComplaintCardResolved() {
    val mockComplaint = Complaint(
        id = 2,
        clientName = "سارة عبد العزيز",
        issueType = "نقص في أدوات الصيانة",
        technicianName = "فهد الجارالله",
        status = ComplaintStatus.RESOLVED,
        timeAgo = "أمس"
    )

    Box(modifier = Modifier.padding(8.dp)) {
        ComplaintCard(
            complaint = mockComplaint,
            onHandleClick = { }
        )
    }
}