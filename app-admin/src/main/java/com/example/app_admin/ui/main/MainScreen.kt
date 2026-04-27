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

    // 🔥 شاشة التفاصيل
    if (selectedTechnician != null) {
        TechnicianDetailScreen(
            technician = selectedTechnician!!,
            onBack = { selectedTechnician = null }
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

                NavigationBar(containerColor = Color.White) {

                    NavigationBarItem(
                        selected = selectedBottomTab == 0,
                        onClick = { selectedBottomTab = 0 },
                        icon = { Icon(Icons.Default.GridView, null) },
                        label = { Text("نظرة عامة") }
                    )

                    NavigationBarItem(
                        selected = selectedBottomTab == 1,
                        onClick = { selectedBottomTab = 1 },
                        icon = { Icon(Icons.Default.Assignment, null) },
                        label = { Text("الطلبات") }
                    )

                    NavigationBarItem(
                        selected = selectedBottomTab == 2,
                        onClick = { selectedBottomTab = 2 },
                        icon = { Icon(Icons.Default.Engineering, null) },
                        label = { Text("الفنيين", fontSize = 10.sp) }
                    )

                    NavigationBarItem(
                        selected = selectedBottomTab == 3,
                        onClick = { selectedBottomTab = 3 },
                        icon = { Icon(Icons.Default.AttachMoney, null) },
                        label = { Text("المالية") }
                    )

                    NavigationBarItem(
                        selected = selectedBottomTab == 4,
                        onClick = { selectedBottomTab = 4 },
                        icon = { Icon(Icons.Default.MoreHoriz, null) },
                        label = { Text("المزيد") }
                    )
                }
            }
        }
    ) { padding ->

        Box(modifier = Modifier.padding(padding)) {

            when (selectedBottomTab) {

                2 -> TechniciansScreen(
                    onTechnicianClick = { tech ->
                        selectedTechnician = tech
                    },

                    // 🔥 الحل النهائي هنا
                    onComplaintClick = { }
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