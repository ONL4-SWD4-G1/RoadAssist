package com.example.roadassist.features.services.fuel.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roadassist.components.DropdownField
import com.example.roadassist.components.PrimaryButton
import com.example.roadassist.components.SectionHeader
import com.example.roadassist.components.UserTopBar
import com.example.roadassist.components.VehicleDetailsSection
import com.example.roadassist.features.services.fuel.viewmodel.FuelViewModel

private val fuelTypes = listOf("Petrol", "Diesel", "CNG", "Electric (charge assist)", "Other")
private val fuelAmounts = listOf("2 lt", "5 lt", "10 lt", "15 lt", "20 lt", "Full tank")

@Composable
fun FuelServiceScreen(
    onNavigateBack: () -> Unit,
    onBookService: () -> Unit,
    viewModel: FuelViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { UserTopBar("Fuel", onNavigateBack) },
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
            SectionHeader("Fuel requirement")
            Spacer(Modifier.height(12.dp))
            DropdownField(
                label = "Fuel type",
                options = fuelTypes,
                selected = uiState.fuelType,
                onSelected = { viewModel.onFuelTypeChanged(it) },
            )
            Spacer(Modifier.height(12.dp))
            DropdownField(
                label = "Fuel required (in lt)",
                options = fuelAmounts,
                selected = uiState.fuelAmount,
                onSelected = { viewModel.onFuelAmountChanged(it) },
            )
            Spacer(Modifier.height(80.dp))
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun FuelServiceScreenPreview() {
    FuelServiceScreen(onNavigateBack = {}, onBookService = {})
}
