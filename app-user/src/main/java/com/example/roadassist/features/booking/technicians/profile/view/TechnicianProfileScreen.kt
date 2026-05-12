package com.example.roadassist.features.booking.technicians.profile.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Construction
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import com.example.roadassist.components.PrimaryButton
import com.example.roadassist.components.SectionHeader
import com.example.roadassist.components.UserTopBar
import com.example.roadassist.features.booking.technicians.profile.viewmodel.TechnicianProfileViewModel
import com.example.roadassist.theme.CardBg
import com.example.roadassist.theme.GreenSuccess
import com.example.roadassist.theme.LightOrange
import com.example.roadassist.theme.OrangeButton
import com.example.roadassist.theme.RoadAssistTheme
import com.example.roadassist.theme.TextPrimary
import com.example.roadassist.theme.TextSecondary

@Composable
fun TechnicianProfileScreen(
    technicianId: String,
    onConfirm: () -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: TechnicianProfileViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(technicianId) { viewModel.loadTechnician(technicianId) }

    val tech = uiState.technician ?: return

    if (uiState.showBookingSuccess) {
        BookingSubmittedDialog(onTrack = {
            viewModel.onBookingSuccessDismissed()
            onConfirm()
        })
    }

    Scaffold(
        topBar = { UserTopBar("Technician Profile", onNavigateBack) },
        containerColor = Color.White,
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(20.dp))
            Box {
                Image(
                    painter = painterResource(tech.image),
                    contentDescription = tech.name,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                )
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(GreenSuccess, CircleShape)
                        .align(Alignment.BottomEnd)
                        .border(3.dp, Color.White, CircleShape),
                )
            }
            Spacer(Modifier.height(12.dp))
            Text(tech.name, fontWeight = FontWeight.Bold, fontSize = 22.sp)
            Text(
                "${tech.role} • ${tech.experienceYears} years exp.",
                color = OrangeButton, fontWeight = FontWeight.Medium, fontSize = 14.sp,
            )
            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                PrimaryButton(
                    "Call", onClick = {},
                    color = CardBg, textColor = TextPrimary, iconTint = TextPrimary,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    icon = Icons.Outlined.Phone,
                )
                PrimaryButton(
                    "Message", onClick = {},
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    icon = Icons.Filled.ChatBubbleOutline,
                )
            }
            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                StatCard(
                    "RATING",
                    "${tech.rating}/5",
                    Icons.Outlined.StarOutline,
                    Modifier.weight(1f)
                )
                StatCard(
                    "JOBS DONE",
                    "${tech.jobsDone}",
                    Icons.Outlined.Construction,
                    Modifier.weight(1f)
                )
                StatCard(
                    "RESPONSE",
                    tech.responseTime,
                    Icons.Outlined.Schedule,
                    Modifier.weight(1f)
                )
            }
            Spacer(Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                SectionHeader("Expertise")
                Spacer(Modifier.height(8.dp))
                Text(tech.about, color = TextSecondary, fontSize = 16.sp, lineHeight = 22.sp)
                Spacer(Modifier.height(12.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    tech.expertise.forEach { tag ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .background(LightOrange)
                                .padding(horizontal = 12.dp, vertical = 6.dp),
                        ) {
                            Text(
                                tag,
                                color = OrangeButton,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(CardBg),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(12.dp)
                ) {
                    Icon(Icons.Outlined.LocationOn, null, tint = OrangeButton)
                    Spacer(Modifier.width(8.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            "SERVICE AREA",
                            fontSize = 11.sp,
                            color = TextSecondary,
                            letterSpacing = 1.sp
                        )
                        Text(tech.serviceArea, fontWeight = FontWeight.Bold)
                    }
                    Button(
                        onClick = { viewModel.onConfirmClicked() },
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = OrangeButton),
                    ) {
                        Text(
                            "Confirm\nTechnician",
                            color = Color.White, fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center, fontSize = 13.sp,
                        )
                        Spacer(Modifier.width(4.dp))
                        Icon(
                            Icons.AutoMirrored.Default.ArrowForward,
                            null,
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TechnicianProfilePreview() {
    RoadAssistTheme {
        TechnicianProfileScreen(technicianId = "t1", onConfirm = {}, onNavigateBack = {})
    }
}
