package com.example.roadassist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.roadassist.ui.theme.Background
import com.example.roadassist.ui.theme.DarkBlue
import com.example.roadassist.ui.theme.TextDark
import com.example.roadassist.ui.theme.White

@Composable
fun ServicesScreen(navController: NavController, onBackClick: () -> Unit = {}) {
    val services = listOf(
        ServiceItem(Icons.Default.DirectionsCar,"Towing"),
        ServiceItem(Icons.Default.TireRepair,"Flat tyre"),
        ServiceItem(Icons.Default.LocalGasStation,"Fuel"),
        ServiceItem(Icons.Default.BatteryChargingFull,"Battery"),
        ServiceItem(Icons.Default.Construction,"Brake"),
        ServiceItem(Icons.Default.Settings,"Engine"),
        ServiceItem(Icons.Default.VpnKey,"Key retrieval"),
        ServiceItem(Icons.Default.MoreHoriz,"Others")
    )

    Scaffold(
        bottomBar = { BottomNavBar(navController = navController, selectedIndex = 1) },
        containerColor = Background
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            TopBarWithBack(title = "Services", onBackClick = onBackClick)

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                "Select service",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = TextDark,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            val rows = services.chunked(2)

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                rows.forEach { rowItems ->
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        rowItems.forEach { service ->
                            LargeServiceCard(
                                service = service,
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    if (service.label == "Others") {
                                        navController.navigate(Routes.OTHER_SERVICE)
                                    }
                                }
                            )
                        }
                        if (rowItems.size == 1) Spacer(modifier = Modifier.weight(1f))
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Composable
fun LargeServiceCard(service: ServiceItem, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clip(RoundedCornerShape(14.dp))
            .background(White)
            .clickable(onClick = onClick)
            .padding(20.dp)
    ) {

        Icon(
            imageVector = service.icon,
            contentDescription = service.label,
            tint = DarkBlue,
            modifier = Modifier.size(40.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            service.label,
            fontSize = 13.sp,
            color = TextDark,
            fontWeight = FontWeight.Medium
        )
    }
}
