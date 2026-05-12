package com.example.roadassist.features.booking.tracking.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Engineering
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roadassist.components.HomeBottomNav
import com.example.roadassist.components.PrimaryButton
import com.example.roadassist.features.booking.tracking.viewmodel.TrackingViewModel
import com.example.roadassist.navigation.Routes
import com.example.roadassist.theme.NavyBlue
import com.example.roadassist.theme.OffWhite
import com.example.roadassist.theme.OrangeButton
import com.example.roadassist.theme.RoadAssistTheme

@Composable
fun ServiceTrackingScreen(
    technicianId: String,
    onMakePayment: () -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToServices: () -> Unit,
    onNavigateToHelp: () -> Unit,
    viewModel: TrackingViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(technicianId) { viewModel.loadTechnician(technicianId) }

    val technician = uiState.technician ?: return
    Scaffold(
        containerColor = OffWhite,
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(NavyBlue)
                    .clip(RoundedCornerShape(bottomStart = 18.dp, bottomEnd = 18.dp))
                    .padding(horizontal = 16.dp, vertical = 14.dp),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Default.ArrowBack, null, tint = Color.White)
                    }
                    Text(
                        "Track",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }
        },
        bottomBar = {
            HomeBottomNav(selected = Routes.TRACK, onNavigate = {
                when (it) {
                    Routes.SERVICES -> onNavigateToServices()
                    Routes.CONTACT -> onNavigateToHelp()
                    Routes.HOME -> onNavigateToHome()
                }
            })
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color(0xFFDDE8DD))
            ) {
                Box(
                    modifier = Modifier
                        .padding(start = 60.dp, top = 40.dp)
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .align(Alignment.TopStart),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        Icons.Outlined.Person,
                        null,
                        tint = NavyBlue,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(end = 48.dp, bottom = 60.dp)
                        .align(Alignment.BottomEnd),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                            .padding(horizontal = 10.dp, vertical = 4.dp),
                    ) {
                        Text(
                            "${uiState.etaMinutes} min",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }
                    Spacer(Modifier.height(4.dp))
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            Icons.Outlined.Engineering,
                            null,
                            tint = NavyBlue,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(4.dp),
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(technician.image),
                        contentDescription = technician.name,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape),
                    )
                    Spacer(Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(technician.name, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        Text(technician.serviceArea, color = Color(0xFF6B7280), fontSize = 13.sp)
                        Text("OTP: ${uiState.otp}", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(NavyBlue),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            Icons.Outlined.Phone,
                            null,
                            tint = Color.White,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                OutlinedButton(
                    onClick = onNavigateBack,
                    shape = RoundedCornerShape(50),
                    border = BorderStroke(1.dp, OrangeButton),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                ) {
                    Text(
                        "Cancel Booking",
                        color = OrangeButton,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                }
                Text(
                    "Service Completed?",
                    textAlign = TextAlign.Center,
                    color = Color(0xFF6B7280),
                    fontSize = 13.sp,
                    modifier = Modifier.fillMaxWidth(),
                )
                PrimaryButton("Make Payment", onMakePayment)
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun ServiceTrackingScreenPreview() {
    RoadAssistTheme {
        ServiceTrackingScreen(
            "t1",
            onMakePayment = {},
            onNavigateBack = {},
            onNavigateToHome = {},
            onNavigateToServices = {},
            onNavigateToHelp = {},
        )
    }
}
