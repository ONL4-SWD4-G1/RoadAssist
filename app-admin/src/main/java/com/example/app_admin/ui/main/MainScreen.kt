// ui/main/MainScreen.kt
package com.example.app_admin.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_admin.model.Technician
import com.example.app_admin.model.TechnicianStatus
import com.example.app_admin.ui.complaints.Complaint
import com.example.app_admin.ui.technicians.TechnicianDetailScreen
import com.example.app_admin.ui.technicians.TechniciansScreen

@Composable
fun MainScreen() {
    var selectedBottomTab by remember { mutableStateOf(2) }
    var selectedTechnician by remember { mutableStateOf<Technician?>(null) }
    var selectedComplaint by remember { mutableStateOf<Complaint?>(null) }

    if (selectedTechnician != null) {
        TechnicianDetailScreen(
            technician = selectedTechnician!!,
            onBack = { selectedTechnician = null }
        )
        return
    }

    if (selectedComplaint != null) {
        // هنا نعيد استخدام نفس شاشة التفاصيل (أو تعملي شاشة خاصة بعدين)
        TechnicianDetailScreen(
            technician = mapComplaintToTechnician(selectedComplaint!!),
            onBack = { selectedComplaint = null }
        )
        return
    }

    Scaffold(
        containerColor = Color(0xffF8F7F6),
        bottomBar = {
            Column {

                HorizontalDivider(
                    color = Color(0xFFE2E8F0),
                    thickness = 1.dp
                )
                NavigationBar(
                    containerColor = Color.White,
                ) {
                    NavigationBarItem(
                        selected = selectedBottomTab == 0,
                        onClick = { selectedBottomTab = 0 },
                        icon = { Icon(Icons.Default.GridView, contentDescription = null) },
                        label = { Text("نظرة عامة") },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFFEC9513),
                            selectedTextColor = Color(0xFFEC9513),
                            unselectedIconColor = Color(0xFF94A3B8),
                            unselectedTextColor = Color(0xFF94A3B8),
                            indicatorColor = Color(0xFFEC9513).copy(alpha = 0.1f)
                        )
                    )
                    NavigationBarItem(
                        selected = selectedBottomTab == 1,
                        onClick = { selectedBottomTab = 1 },
                        icon = { Icon(Icons.Default.Assignment, contentDescription = null) },
                        label = { Text("الطلبات") },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFFEC9513),
                            selectedTextColor = Color(0xFFEC9513),
                            unselectedIconColor = Color(0xFF94A3B8),
                            unselectedTextColor = Color(0xFF94A3B8),
                            indicatorColor = Color(0xFFEC9513).copy(alpha = 0.1f)
                        )
                    )
                    NavigationBarItem(
                        selected = selectedBottomTab == 2,
                        onClick = { selectedBottomTab = 2 },
                        icon = { Icon(Icons.Default.Engineering, contentDescription = null) },
                        label = { Text("الفنيين", fontSize = 10.sp) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFFEC9513),
                            selectedTextColor = Color(0xFFEC9513),
                            unselectedIconColor = Color(0xFF94A3B8),
                            unselectedTextColor = Color(0xFF94A3B8),
                            indicatorColor = Color(0xFFEC9513).copy(alpha = 0.1f)
                        )
                    )
                    NavigationBarItem(
                        selected = selectedBottomTab == 3,
                        onClick = { selectedBottomTab = 3 },
                        icon = { Icon(Icons.Default.AttachMoney, contentDescription = null) },
                        label = { Text("المالية") },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFFEC9513),
                            selectedTextColor = Color(0xFFEC9513),
                            unselectedIconColor = Color(0xFF94A3B8),
                            unselectedTextColor = Color(0xFF94A3B8),
                            indicatorColor = Color(0xFFEC9513).copy(alpha = 0.1f)
                        )
                    )
                    NavigationBarItem(
                        selected = selectedBottomTab == 4,
                        onClick = { selectedBottomTab = 4 },
                        icon = { Icon(Icons.Default.MoreHoriz, contentDescription = null) },
                        label = { Text("المزيد") },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFFEC9513),
                            selectedTextColor = Color(0xFFEC9513),
                            unselectedIconColor = Color(0xFF94A3B8),
                            unselectedTextColor = Color(0xFF94A3B8),
                            indicatorColor = Color(0xFFEC9513).copy(alpha = 0.1f)
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedBottomTab) {
                2 -> TechniciansScreen(
                    onTechnicianClick = { tech -> selectedTechnician = tech },
                    onComplaintClick = { complaint -> selectedComplaint = complaint }
                )
                else -> Text("")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}
fun mapComplaintToTechnician(complaint: Complaint): Technician {
    return Technician(
        id = complaint.id,
        name = complaint.technicianName,
        phone = "غير متوفر",
        city = "غير محدد",
        specialty = complaint.issueType,
        experience = 0,
        hasCar = false,
        rating = 0.0,
        completedJobs = 0,
        status = TechnicianStatus.ACTIVE
    )
}