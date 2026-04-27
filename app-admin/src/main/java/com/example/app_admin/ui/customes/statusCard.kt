package com.example.app_admin.ui.technicians

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_admin.model.Technician
import com.example.app_admin.model.TechnicianStatus
import com.example.app_admin.model.sampleTechnicians
import com.example.app_admin.ui.complaints.ComplaintsScreen
import com.example.app_admin.ui.model.Complaint

@Composable
fun TechniciansScreen(
    onTechnicianClick: (Technician) -> Unit,
    onComplaintClick: (Complaint) -> Unit
) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("الكل", "النشطين", "قيد التسجيل", "الموقوفين", "الشكاوى")

    Column(modifier = Modifier.fillMaxSize()) {
        StatsRow()
        ScrollableTabRow(
            selectedTabIndex = selectedTab,
            edgePadding = 8.dp,
            containerColor = Color.White,
            contentColor = Color(0xFFF5A623)
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            text = title,
                            fontSize = 13.sp,
                            fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                )
            }
        }

        when (selectedTab) {
            0 -> AllTechniciansTab(onTechnicianClick = onTechnicianClick)
            1 -> FilteredTechniciansTab(TechnicianStatus.ACTIVE,
                onTechnicianClick)
            2 -> FilteredTechniciansTab(TechnicianStatus.WAITING,
                onTechnicianClick)
            3 -> FilteredTechniciansTab(TechnicianStatus.SUSPENDED,
                onTechnicianClick)
            4 -> ComplaintsScreen(
                onHandleComplaint = { complaint ->

                    onComplaintClick(complaint)
                }
            )
        }
    }
}

@Composable
fun AllTechniciansTab(onTechnicianClick: (Technician) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(sampleTechnicians) { tech ->
            TechnicianCard(
                technician = tech,
                onDetailsClick = { onTechnicianClick(tech) }
            )
        }
    }
}

@Composable
fun FilteredTechniciansTab(status: TechnicianStatus, onTechnicianClick: (Technician) -> Unit) {
    val filtered = sampleTechnicians.filter { it.status == status }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(filtered) { tech ->
            TechnicianCard(
                technician = tech,
                onDetailsClick = { onTechnicianClick(tech) }
            )
        }
    }
}

@Composable
fun StatsRow() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        StatCard("نشط الآن", "٤٢",
            "%٦٠+", Color(0xFFEC9513), Modifier.weight(1f))
        StatCard("قيد الانتظار", "١٥",
            "%٢٠-", Color(0xFFCBD5E1), Modifier.weight(1f))
        StatCard("تقييم منخفض", "٣",
            "%٠-", Color(0xFFF87171), Modifier.weight(1f))
    }
}

@Composable
fun StatCard(title: String, value: String, subtitle: String, borderColor: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(Color.White, RoundedCornerShape(12.dp))
            .drawBehind {
                val strokeWidth = 4.dp.toPx()
                drawLine(
                    color = borderColor,
                    start = Offset(0f, size.height - strokeWidth / 2),
                    end = Offset(size.width, size.height - strokeWidth / 2),
                    strokeWidth = strokeWidth
                )
            }
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(title, fontSize = 11.sp, color = Color(0xff64748B))
            Text(value, fontSize = 22.sp, fontWeight = FontWeight.Bold,
                color = Color(0xff1E293B))
            Text(subtitle, fontSize = 11.sp, color = Color(0xFF94A3B8))
        }
    }
}