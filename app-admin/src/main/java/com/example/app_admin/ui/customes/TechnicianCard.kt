package com.example.app_admin.ui.technicians

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_admin.model.Technician
import com.example.app_admin.model.TechnicianStatus
import com.example.app_admin.model.sampleTechnicians
import com.example.app_admin.R

@Composable
fun TechnicianCard(
    technician: Technician,
    onDetailsClick: () -> Unit,
    onAccept: (() -> Unit)? = null,
    onReject: (() -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        border = BorderStroke(1.dp, color = Color(0xffF1F5F9)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(IntrinsicSize.Min),
            modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .weight(1f)
            ) {

                StatusBadge(status = technician.status)

//                Spacer(modifier = Modifier.height(6.dp))


                Text(
                    text = technician.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = androidx.compose.ui.text.style.TextAlign.End,
                    color = Color(0xff1E293B)
                )


                Text(
                    text = technician.specialty,
                    fontSize = 13.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(top = 2.dp)
                        .fillMaxWidth(),
                    textAlign = androidx.compose.ui.text.style.TextAlign.End
                )


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "${technician.completedJobs} مهمة مكتملة  •  ",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "${technician.rating} ",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Icon(
                        Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFFF5A623),
                        modifier = Modifier.size(14.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))


                if (technician.status == TechnicianStatus.NEW_REQUEST) {
                    Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                        OutlinedButton(
                            onClick = { onDetailsClick() },
                            modifier = Modifier.weight(2f),
                            border = BorderStroke(width = 1.dp,
                                color = Color(0xffE2E8F0)),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text("عرض التفاصيل", fontSize = 10.sp,
                                fontWeight = FontWeight.ExtraBold, color = Color(0xff1E293B))
                        }
                        Button(
                            onClick = { onAccept?.invoke() },
                            modifier = Modifier.weight(2f),
                            colors = ButtonDefaults
                                .buttonColors(containerColor = Color(0xFFF5A623)),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text("قبول", fontSize = 10.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
                        }
                        OutlinedButton(
                            onClick = { onReject?.invoke() },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults
                                .outlinedButtonColors(contentColor = Color.Red),
                            border = BorderStroke(width = 1.dp, color = Color(0xffEF4444)),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text("رفض", fontSize = 10.sp)
                        }
                    }
                } else {
                    Button(
                        onClick = onDetailsClick,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF5A623)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("عرض التفاصيل", fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                            )
                    }
                }
            }


            Box(
                modifier = Modifier
                    .width(90.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp))
                    .background(Color(0xFF1A6B5A)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.technician),
                    contentDescription = null,
                    modifier = Modifier
                        .width(90.dp)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }

    }
}

@Composable
fun StatusBadge(status: TechnicianStatus) {
    val (text, color) = when (status) {
        TechnicianStatus.ACTIVE -> "نشط" to Color(0xFF15803D)
        TechnicianStatus.WAITING -> "قيد الانتظار" to Color(0xFFA16207)
        TechnicianStatus.SUSPENDED -> "موقوف" to Color(0xFFB91C1C)
        TechnicianStatus.NEW_REQUEST -> "طلب جديد" to Color(0xFFC2410C)
    }
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(color.copy(alpha = 0.15f))
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Text(text, color = color, fontSize = 11.sp, fontWeight = FontWeight.Bold)
    }
}


@Preview(showBackground = true)
@Composable
fun TechnicianCardPreview() {
    Column {
        sampleTechnicians.forEach { tech ->
            TechnicianCard(
                technician = tech,
                onDetailsClick = {}
            )
        }
    }
}