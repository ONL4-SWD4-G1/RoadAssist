package com.example.app_admin.technicians.view.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_admin.R
import com.example.app_admin.sampleTechnicians
import com.example.app_admin.shared.StatusBadge
import com.example.app_admin.technicians.model.Technician
import com.example.app_admin.technicians.model.TechnicianStatus

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
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .weight(1f)
            ) {

                StatusBadge(
                    label = technician.status.label,
                    color = technician.status.color,
                )

                Text(
                    text = technician.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    color = Color(0xff1E293B)
                )

                Text(
                    text = technician.specialty,
                    fontSize = 13.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(top = 2.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.End
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
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        OutlinedButton(
                            onClick = { onDetailsClick() },
                            modifier = Modifier.weight(2f),
                            border = BorderStroke(
                                width = 1.dp,
                                color = Color(0xffE2E8F0)
                            ),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "عرض التفاصيل",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xff1E293B)
                            )
                        }
                        Button(
                            onClick = { onAccept?.invoke() },
                            modifier = Modifier.weight(2f),
                            colors = ButtonDefaults
                                .buttonColors(containerColor = Color(0xFFF5A623)),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "قبول",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
                            )
                        }
                        OutlinedButton(
                            onClick = { onReject?.invoke() },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults
                                .outlinedButtonColors(contentColor = Color.Red),
                            border = BorderStroke(width = 1.dp, color = Color(0xffEF4444)),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(text = "رفض", fontSize = 10.sp)
                        }
                    }
                } else {
                    Button(
                        onClick = onDetailsClick,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF5A623)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "عرض التفاصيل",
                            fontSize = 11.sp,
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