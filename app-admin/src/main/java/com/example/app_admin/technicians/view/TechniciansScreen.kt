package com.example.app_admin.technicians.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.app_admin.complaints.model.Complaint
import com.example.app_admin.complaints.view.ComplaintsScreen
import com.example.app_admin.root.navigation.Screen
import com.example.app_admin.sampleTechnicians
import com.example.app_admin.shared.RoadAssistTabRow
import com.example.app_admin.shared.RoadAssistTopAppBar
import com.example.app_admin.technicians.model.Technician
import com.example.app_admin.technicians.model.TechnicianStatus
import com.example.app_admin.technicians.view.component.StatCard
import com.example.app_admin.technicians.view.component.TechnicianCard
import com.example.app_admin.theme.DarkNavy

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TechniciansScreen(
    onTechnicianClick: (Technician) -> Unit,
    onComplaintClick: (Complaint) -> Unit,
    navController: NavHostController,
    onMenuClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {}
) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("الكل", "النشطين", "قيد التسجيل", "الموقوفين", "الشكاوى")

    // Force RTL for Arabic support
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold(
            topBar = {
                RoadAssistTopAppBar(
                    title = "إدارة الفنيين",
                    navigationIcon = {
                        IconButton(onClick = onMenuClick) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = null,
                                tint = DarkNavy
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = onSearchClick) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                                tint = DarkNavy
                            )
                        }
                        IconButton(onClick = onNotificationClick) {
                            Icon(
                                imageVector = Icons.Default.NotificationsNone,
                                contentDescription = null,
                                tint = DarkNavy
                            )
                        }
                    },
                    bottomContent = {
                        RoadAssistTabRow(
                            tabs = tabs,
                            selectedTabIndex = selectedTab,
                            onTabSelected = { selectedTab = it },
                            isScrollable = true
                        )
                    }
                )
            },
            containerColor = Color(0xFFF8FAFC) // Professional light background
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                StatsRow()

                when (selectedTab) {
                    0 -> AllTechniciansTab(onTechnicianClick = onTechnicianClick)

                    1 -> FilteredTechniciansTab(
                        status = TechnicianStatus.ACTIVE,
                        onTechnicianClick = onTechnicianClick
                    )

                    2 -> FilteredTechniciansTab(
                        status = TechnicianStatus.WAITING,
                        onTechnicianClick = onTechnicianClick
                    )

                    3 -> FilteredTechniciansTab(
                        status = TechnicianStatus.SUSPENDED,
                        onTechnicianClick = onTechnicianClick
                    )

                    4 -> ComplaintsScreen(
                        onHandleComplaint = { complaint ->
                            navController.navigate(Screen.ComplaintDetail(complaintId = complaint.id))
                        }
                    )
                }
            }
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
fun FilteredTechniciansTab(
    status: TechnicianStatus,
    onTechnicianClick: (Technician) -> Unit
) {
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
        modifier = Modifier.Companion
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        StatCard(
            title = "نشط الآن",
            value = "٤٢",
            subtitle = "%٦٠+",
            borderColor = Color(0xFFEC9513),
            modifier = Modifier.weight(1f)
        )
        StatCard(
            title = "قيد الانتظار",
            value = "١٥",
            subtitle = "%٢٠-",
            borderColor = Color(0xFFCBD5E1),
            modifier = Modifier.weight(1f)
        )
        StatCard(
            title = "تقييم منخفض",
            value = "٣",
            subtitle = "%٠-",
            borderColor = Color(0xFFF87171),
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true, name = "Full Screen Preview", locale = "ar")
@Composable
fun TechniciansScreenPreview() {
    MaterialTheme {
        TechniciansScreen(
            onTechnicianClick = {},
            onComplaintClick = {},
            onMenuClick = {},
            onSearchClick = {},
            onNotificationClick = {},
            navController = NavHostController(LocalContext.current)
        )
    }
}