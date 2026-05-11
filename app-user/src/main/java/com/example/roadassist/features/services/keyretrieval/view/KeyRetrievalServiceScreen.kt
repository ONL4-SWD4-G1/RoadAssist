package com.example.roadassist.features.services.keyretrieval.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CloudUpload
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roadassist.components.PrimaryButton
import com.example.roadassist.components.RoundedTextField
import com.example.roadassist.components.SectionHeader
import com.example.roadassist.components.UserTopBar
import com.example.roadassist.components.VehicleDetailsSection
import com.example.roadassist.features.services.keyretrieval.viewmodel.KeyRetrievalViewModel
import com.example.roadassist.theme.NavyBlue
import com.example.roadassist.theme.TextSecondary

@Composable
fun KeyRetrievalServiceScreen(
    onNavigateBack: () -> Unit,
    onBookService: () -> Unit,
    viewModel: KeyRetrievalViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { UserTopBar("Key retrieval", onNavigateBack) },
        containerColor = Color.White,
        bottomBar = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                PrimaryButton("Book Service", onBookService)
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
        ) {
            VehicleDetailsSection(uiState.vehicleDetails) { viewModel.onVehicleDetailsChanged(it) }
            Spacer(Modifier.height(24.dp))
            SectionHeader("Lock details")
            Spacer(Modifier.height(12.dp))
            RoundedTextField(
                value = uiState.lockManufacturer,
                onValueChange = { viewModel.onLockManufacturerChanged(it) },
                placeholder = "Lock Manufacturer",
            )
            Spacer(Modifier.height(16.dp))
            SectionHeader("Proof of ownership")
            Spacer(Modifier.height(8.dp))
            Text(
                "Upload image of ownership document (RC book, insurance, etc.)",
                fontSize = 13.sp,
                color = TextSecondary
            )
            Spacer(Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clickable { viewModel.onOwnershipUploaded() }
                    .background(
                        color = if (uiState.ownershipUploaded) Color(0xFFE8F5E9) else Color(
                            0xFFF5F5F5
                        ),
                        shape = RoundedCornerShape(12.dp),
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        Icons.Outlined.CloudUpload, null,
                        tint = if (uiState.ownershipUploaded) Color(0xFF4CAF50) else NavyBlue,
                        modifier = Modifier.size(36.dp),
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        if (uiState.ownershipUploaded) "Document uploaded ✓" else "Tap to upload image",
                        fontSize = 14.sp,
                        color = if (uiState.ownershipUploaded) Color(0xFF4CAF50) else TextSecondary,
                        fontWeight = if (uiState.ownershipUploaded) FontWeight.SemiBold else FontWeight.Normal,
                    )
                }
            }
            Spacer(Modifier.height(20.dp))
            SectionHeader("Special instructions")
            Spacer(Modifier.height(8.dp))
            RoundedTextField(
                value = uiState.specialInstructions,
                onValueChange = { viewModel.onSpecialInstructionsChanged(it) },
                placeholder = "Add any special instructions here…",
                maxLines = 4,
            )
            Spacer(Modifier.height(80.dp))
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun KeyRetrievalServiceScreenPreview() {
    KeyRetrievalServiceScreen(onNavigateBack = {}, onBookService = {})
}
