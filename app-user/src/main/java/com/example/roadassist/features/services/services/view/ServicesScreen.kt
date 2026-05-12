package com.example.roadassist.features.services.services.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roadassist.components.HomeBottomNav
import com.example.roadassist.components.UserTopBar
import com.example.roadassist.fakedata.ServiceItem
import com.example.roadassist.features.home.home.view.ServiceIconView
import com.example.roadassist.features.home.home.view.serviceIcon
import com.example.roadassist.features.services.services.viewmodel.ServicesViewModel
import com.example.roadassist.navigation.Routes
import com.example.roadassist.theme.OffWhite
import com.example.roadassist.theme.OrangeButton
import com.example.roadassist.theme.RoadAssistTheme
import com.example.roadassist.theme.TextPrimary

@Composable
fun ServicesScreen(
    onServiceSelected: (String) -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateToTrack: () -> Unit,
    onNavigateToHelp: () -> Unit,
    viewModel: ServicesViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { UserTopBar("Services", onNavigateBack) },
        containerColor = OffWhite,
        bottomBar = {
            HomeBottomNav(selected = Routes.SERVICES, onNavigate = {
                when (it) {
                    Routes.TRACK -> onNavigateToTrack()
                    Routes.CONTACT -> onNavigateToHelp()
                    Routes.HOME -> onNavigateBack()
                }
            })
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
        ) {
            Text(
                "Select service",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary,
                modifier = Modifier.padding(bottom = 16.dp),
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(uiState.services) { service ->
                    ServiceCard(service = service, onClick = { onServiceSelected(service.route) })
                }
            }
        }
    }
}

@Composable
private fun ServiceCard(service: ServiceItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFFF3E0)),
                contentAlignment = Alignment.Center,
            ) {
                ServiceIconView(
                    icon = serviceIcon(service.id),
                    contentDescription = service.label,
                    tint = OrangeButton,
                    modifier = Modifier.size(28.dp),
                )
            }
            androidx.compose.foundation.layout.Spacer(Modifier.size(12.dp))
            Text(
                service.label,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ServicesScreenPreview() {
    RoadAssistTheme {
        ServicesScreen(
            onServiceSelected = {},
            onNavigateBack = {},
            onNavigateToTrack = {},
            onNavigateToHelp = {},
        )
    }
}
