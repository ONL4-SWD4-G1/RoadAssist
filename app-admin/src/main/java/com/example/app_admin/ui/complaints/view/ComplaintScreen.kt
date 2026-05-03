package com.example.app_admin.ui.complaints.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
// استيراد الـ data class والـ enum والبيانات
import com.example.app_admin.ui.complaints.model.Complaint
import com.example.app_admin.ui.complaints.model.sampleComplaints
import com.example.app_admin.ui.complaints.view.component.ComplaintCard

@Composable
fun ComplaintsScreen(onHandleComplaint: (Complaint) -> Unit) {
//    var selectedComplaint by remember { mutableStateOf<Complaint?>(null) }

//    if (selectedComplaint == null) {
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
//else {
//       ComplaintDetailsScreen(
//            onBack = { selectedComplaint = null }
//     )
//   }
//}


//@Composable
//fun ComplaintCard(complaint: Complaint, onHandleClick: () -> Unit) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 16.dp, vertical = 6.dp),
//        border = BorderStroke(1.dp, color = Color(0xffF1F5F9)),
//        colors = CardDefaults.cardColors(containerColor = Color.White),
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                ComplaintStatusBadge(status = complaint.status)
//                Column(horizontalAlignment = Alignment.End) {
//                    Text(
//                        text = complaint.clientName,
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 16.sp,
//                        textAlign = TextAlign.End
//                    )
//                    Text(
//                        text = "العميل",
//                        fontSize = 11.sp,
//                        color = Color.Gray,
//                        textAlign = TextAlign.End
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.End
//            ) {
//                Column(
//                    modifier = Modifier.weight(1f),
//                    horizontalAlignment = Alignment.End
//                ) {
//                    Text("نوع العطل", fontSize = 11.sp, color = Color.Gray, textAlign = TextAlign.End)
//                    Text(complaint.issueType, fontSize = 13.sp, fontWeight = FontWeight.Medium, textAlign = TextAlign.End)
//                }
//                Spacer(modifier = Modifier.width(16.dp))
//                Column(
//                    modifier = Modifier.weight(1f),
//                    horizontalAlignment = Alignment.End
//                ) {
//                    Text("الفني المسؤول", fontSize = 11.sp, color = Color.Gray,
//                        textAlign = TextAlign.End)
//                    Text(complaint.technicianName,
//                        fontSize = 13.sp,
//                        fontWeight = FontWeight.Medium,
//                        textAlign = TextAlign.End)
//                }
//            }
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            Text(
//                text = "تاريخ الشكوى: ${complaint.timeAgo}",
//                fontSize = 11.sp,
//                color = Color.Gray,
//                modifier = Modifier.fillMaxWidth(),
//                textAlign = TextAlign.End
//            )
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            HorizontalDivider(
//                color = Color(0xFFE2E8F0),
//                thickness = 1.dp
//            )
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            if (complaint.status == ComplaintStatus.RESOLVED) {
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    IconButton(onClick = {}) {
//                        Icon(
//                            imageVector = Icons.Default.Visibility,
//                            contentDescription = "",
//                            tint = Color(0xFF94A3B8)
//                        )
//                    }
//                    Button(
//                        onClick = onHandleClick,
//                        modifier = Modifier
//                            .weight(1f)
//                            .height(36.dp),
//                        colors = ButtonDefaults.
//                        buttonColors(containerColor = Color(0xFFF1F5F9)),
//                        shape = RoundedCornerShape(8.dp)
//                    ) {
//                        Text("مكتملة", color = Color(0xff94A3B8))
//                    }
//                }
//            } else {
//                Button(
//                    onClick = onHandleClick,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(36.dp),
//                    colors = ButtonDefaults.
//                    buttonColors(containerColor = Color(0xFF1E293B)),
//                    shape = RoundedCornerShape(8.dp)
//                ) {
//                    Text("معالجة الشكوى", color = Color.White)
//                }
//            }
//        }
//    }
//}

//@Composable
//fun ComplaintStatusBadge(status: ComplaintStatus) {
//    val (text, color) = when (status) {
//        ComplaintStatus.OPEN -> "مفتوحة" to Color(0xFFC2410C)
//        ComplaintStatus.UNDER_REVIEW -> "قيد المراجعة" to Color(0xFF1D4ED8)
//        ComplaintStatus.RESOLVED -> "تم الحل" to Color(0xFF15803D)
//    }
//    Box(
//        modifier = Modifier
//            .clip(RoundedCornerShape(20.dp))
//            .background(color.copy(alpha = 0.15f))
//            .padding(horizontal = 8.dp, vertical = 3.dp)
//    ) {
//        Text(text, color = color, fontSize = 11.sp, fontWeight = FontWeight.Medium)
//    }
//}