package com.example.app_admin.root

import CompliantPerson
import MoreScreen
import WarningScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_admin.model.Technician
import com.example.app_admin.ui.finance.view.FinanceScreen
import com.example.app_admin.ui.complaints.model.Complaint
import com.example.app_admin.ui.Screens.ComplaintDetailsScreen
import com.example.app_admin.ui.technicians.view.TechniciansScreen
import com.example.app_admin.ui.screens.TechnicianDetailScreen

@Composable
fun MainScreen() {
    var selectedBottomTab by remember { mutableStateOf(2) }
    var selectedTechnician by remember { mutableStateOf<Technician?>(null) }
    var selectedComplaint by remember { mutableStateOf<Complaint?>(null) }
    var showWarningScreen by remember { mutableStateOf(false) }
    var showUserProfile by remember { mutableStateOf(false) }


    if (selectedTechnician != null) {
        TechnicianDetailScreen(
            technician = selectedTechnician!!,
            onBack = { selectedTechnician = null }
        )
        return
    }

    if (showWarningScreen) {
        WarningScreen(
            onBack = { showWarningScreen = false }
        )
        return
    }


    if (showUserProfile) {
        CompliantPerson(
            onBack = { showUserProfile = false },
            onWarningClick = {
                showWarningScreen = true
            }
        )
        return
    }

    if (selectedComplaint != null) {
        ComplaintDetailsScreen(
            onBack = { selectedComplaint = null },
            onNavigateToWarning = { showWarningScreen = true },
            onNavigateToProfile = { showUserProfile = true }
        )
        return
    }

    Scaffold(
        containerColor = Color(0xffF8F7F6),
        bottomBar = {
            Column {
                HorizontalDivider(color = Color(0xFFE2E8F0), thickness = 1.dp)
                NavigationBar(containerColor = Color.White) {
                    val navItems = listOf(
                        Triple(0, Icons.Default.GridView, "نظرة عامة"),
                        Triple(1, Icons.Default.Assignment, "الطلبات"),
                        Triple(2, Icons.Default.Engineering, "الفنيين"),
                        Triple(3, Icons.Default.AttachMoney, "المالية"),
                        Triple(4, Icons.Default.MoreHoriz, "المزيد")
                    )

                    navItems.forEach { (index, icon, label) ->
                        NavigationBarItem(
                            selected = selectedBottomTab == index,
                            onClick = { selectedBottomTab = index },
                            icon = { Icon(icon,
                                "") },
                            label = { Text(label, fontSize = 10.sp) }
                        )
                    }
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (selectedBottomTab) {
                2 -> TechniciansScreen(
                    onTechnicianClick = { tech -> selectedTechnician = tech },
                    onComplaintClick = { complaint -> selectedComplaint = complaint }
                )
                3 -> FinanceScreen()
                4 -> MoreScreen()
                else -> Text("قيد التطوير", modifier = Modifier.padding(16.dp))
            }
        }
    }
}