package com.example.roadassist.features.services.otherservices.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roadassist.components.PrimaryButton
import com.example.roadassist.components.RoundedTextField
import com.example.roadassist.components.UserTopBar
import com.example.roadassist.features.services.otherservices.viewmodel.OtherServiceViewModel
import com.example.roadassist.theme.Background
import com.example.roadassist.theme.RoadAssistTheme
import com.example.roadassist.theme.TextPrimary

@Composable
fun OtherServiceFormScreen(
    onBackClick: () -> Unit = {},
    onBookService: () -> Unit = {},
    viewModel: OtherServiceViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { UserTopBar("Other Service", onBackClick) },
        containerColor = Background,
        bottomBar = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                PrimaryButton("Book Service", onBookService)
            }
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = com.example.roadassist.theme.White),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Vehicle details", fontWeight = FontWeight.Bold,
                        fontSize = 16.sp, color = TextPrimary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    RoundedTextField(
                        value = uiState.vehicleType,
                        onValueChange = viewModel::onVehicleTypeChange,
                        placeholder = "Vehicle Type"
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    RoundedTextField(
                        value = uiState.manufacturer,
                        onValueChange = viewModel::onManufacturerChange,
                        placeholder = "Manufacturer"
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    RoundedTextField(
                        value = uiState.model,
                        onValueChange = viewModel::onModelChange,
                        placeholder = "Model"
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    RoundedTextField(
                        value = uiState.registrationNum,
                        onValueChange = viewModel::onRegistrationNumChange,
                        placeholder = "Enter Registration number"
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ── Problem Description Card ──────────────────────────────
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = com.example.roadassist.theme.White),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Please describe the problem of vehicle in detail",
                        fontSize = 14.sp, color = TextPrimary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    RoundedTextField(
                        value = uiState.problemDescription,
                        onValueChange = viewModel::onProblemDescriptionChange,
                        placeholder = "Describe issue here...",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        maxLines = 5
                    )
                }
            }

        }
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun OtherServiceFormScreenPreview() {
    RoadAssistTheme {
        OtherServiceFormScreen()
    }
}