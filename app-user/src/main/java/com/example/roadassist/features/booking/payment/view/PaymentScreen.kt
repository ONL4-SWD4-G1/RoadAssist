package com.example.roadassist.features.booking.payment.view

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roadassist.components.PrimaryButton
import com.example.roadassist.components.UserTopBar
import com.example.roadassist.features.booking.payment.viewmodel.PaymentViewModel
import com.example.roadassist.theme.DarkNavyBlue
import com.example.roadassist.theme.DividerColor
import com.example.roadassist.theme.NavyBlue
import com.example.roadassist.theme.OffWhite
import com.example.roadassist.theme.RoadAssistTheme
import com.example.roadassist.theme.TextSecondary

@Composable
fun PaymentScreen(
    serviceId: String,
    technicianId: String,
    onPaymentSuccess: () -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: PaymentViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(serviceId, technicianId) { viewModel.loadData(serviceId, technicianId) }
    val service = uiState.service ?: return
    val technician = uiState.technician ?: return

    if (uiState.showSuccess) {
        PaymentSuccessDialog(onRateUs = onPaymentSuccess)
    }

    Scaffold(
        topBar = { UserTopBar("Payment", onNavigateBack) },
        containerColor = OffWhite,
        bottomBar = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                PrimaryButton(
                    text = "Confirm",
                    onClick = { viewModel.onPaymentConfirmed() },
                )
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            // ── Service summary card ──────────────────────────
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(2.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        "Service Summary",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = DarkNavyBlue,
                    )
                    Spacer(Modifier.height(16.dp))
                    SummaryRow(label = "Service", value = service.label)
                    SummaryRow(label = "Mechanic", value = technician.name)
                    SummaryRow(label = "Location", value = technician.serviceArea)
                }
            }

            // ── Suggested price card ──────────────────────────
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(2.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Outlined.Info,
                            contentDescription = null,
                            tint = DarkNavyBlue,
                            modifier = Modifier.size(18.dp),
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            "Suggested Price Range",
                            fontSize = 16.sp,
                            color = DarkNavyBlue,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Spacer(Modifier.height(12.dp))
                    Text(
                        "E£ ${service.suggestedPriceMin} – ${service.suggestedPriceMax}",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = NavyBlue,
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        service.priceNote,
                        fontSize = 14.sp,
                        color = TextSecondary,
                    )
                    Spacer(Modifier.height(16.dp))
                    HorizontalDivider(color = DividerColor, thickness = 1.dp)
                    Spacer(Modifier.height(12.dp))
                    Text(
                        "Final price is agreed with the mechanic after inspection.",
                        fontSize = 12.sp,
                        color = TextSecondary,
                        lineHeight = 18.sp,
                    )
                }
            }

            // ── Final price input ─────────────────────────────
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(2.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        "Agreed Price",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color(0xFF1A1A2E),
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "Enter the price you agreed on with the mechanic",
                        fontSize = 13.sp,
                        color = Color(0xFF6B7280),
                    )
                    Spacer(Modifier.height(12.dp))
                    OutlinedTextField(
                        value = uiState.agreedPrice,
                        onValueChange = { input ->
                            viewModel.onAgreedPriceChanged(
                                input.filter { c -> c.isDigit() || c == '.' }
                            )
                        },
                        placeholder = { Text("e.g.  75", color = Color(0xFF9CA3AF)) },
                        prefix = { Text("E£  ", fontWeight = FontWeight.SemiBold) },
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = NavyBlue,
                            unfocusedBorderColor = Color(0xFFD1D5DB),
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                        ),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }

            // ── Payment method — cash only ────────────────────
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(2.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        "Payment Method",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color(0xFF1A1A2E),
                    )
                    Spacer(Modifier.height(12.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFF0F7FF))
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(NavyBlue.copy(alpha = 0.1f)),
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(
                                Icons.Outlined.Payments,
                                contentDescription = null,
                                tint = NavyBlue,
                                modifier = Modifier.size(24.dp),
                            )
                        }
                        Spacer(Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                "Cash",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = Color(0xFF1A1A2E),
                            )
                            Text(
                                "Pay directly to the mechanic",
                                fontSize = 12.sp,
                                color = Color(0xFF6B7280),
                            )
                        }
                        Icon(
                            Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = NavyBlue,
                            modifier = Modifier.size(22.dp),
                        )
                    }
                }
            }

            Spacer(Modifier.height(72.dp))
        }
    }
}

// ── Summary row helper ─────────────────────────────────────────
@Composable
private fun SummaryRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(label, fontSize = 14.sp, color = TextSecondary)
        Text(value, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = DarkNavyBlue)
    }
}


@Preview(showBackground = true, showSystemUi = true, name = "Payment Screen - Towing")
@Composable
private fun PaymentTowingPreview() {
    RoadAssistTheme {
        PaymentScreen(
            serviceId = "towing",
            technicianId = "t1",
            onPaymentSuccess = {},
            onNavigateBack = {})
    }
}