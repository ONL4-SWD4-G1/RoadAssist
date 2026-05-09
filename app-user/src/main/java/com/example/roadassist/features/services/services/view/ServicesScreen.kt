package com.example.roadassist.features.services.services.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.roadassist.features.home.home.view.BottomNavBar
import com.example.roadassist.features.home.notification.view.TopBarWithBack
import com.example.roadassist.features.services.services.model.ServiceItem
import com.example.roadassist.features.services.services.viewmodel.ServicesViewModel
import com.example.roadassist.theme.Background
import com.example.roadassist.theme.OrangeAccent
import com.example.roadassist.theme.TextPrimary
import com.example.roadassist.theme.White

@Composable
fun ServicesScreen(
    navController: NavController,
    onBackClick: () -> Unit = {},
    onOtherServiceClick: () -> Unit = {},
    viewModel: ServicesViewModel = viewModel()
) {
    Scaffold(
        bottomBar = { BottomNavBar(navController = navController, selectedIndex = 1) },
        containerColor = Background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            TopBarWithBack(title = "Services", onBackClick = onBackClick)

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                "Select service",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = TextPrimary,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                viewModel.getServiceRows().forEach { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        rowItems.forEach { service ->
                            LargeServiceCard(
                                service = service,
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    if (service.label == "Others") {
                                        onOtherServiceClick()
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
fun LargeServiceCard(
    service: ServiceItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(White)
            .clickable(onClick = onClick)
            .padding(20.dp)
    ) {
        Icon(
            imageVector = service.icon,
            contentDescription = service.label,
            tint = OrangeAccent,
            modifier = Modifier.size(40.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            service.label,
            fontSize = 13.sp,
            color = TextPrimary,
            fontWeight = FontWeight.Medium
        )
    }
}