package com.example.roadassist.features.services.battery.view

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
import com.example.roadassist.components.RoundedTextField
import com.example.roadassist.components.SectionHeader
import com.example.roadassist.components.UserTopBar
import com.example.roadassist.components.VehicleDetailsSection
import com.example.roadassist.features.services.battery.viewmodel.BatteryViewModel

private val batteryConditions = listOf(
    "Dead / won't start",
    "Weak / slow crank",
    "Corroded terminals",
    "Swollen / damaged",
    "Other"
)
private val batteryTypes = listOf("Lead Acid", "AGM", "Gel", "Lithium", "Not sure")
private val budgetRanges =
    listOf("Under ₹2,000", "₹2,000–₹4,000", "₹4,000–₹7,000", "Above ₹7,000", "No preference")

@Composable
fun BatteryServiceScreen(
    onNavigateBack: () -> Unit,
    onBookService: () -> Unit,
    viewModel: BatteryViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { UserTopBar("Battery", onNavigateBack) },
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
            SectionHeader("Battery requirements")
            Spacer(Modifier.height(12.dp))
            DropdownField(
                label = "Current battery condition",
                options = batteryConditions,
                selected = uiState.batteryCondition,
                onSelected = { viewModel.onBatteryConditionChanged(it) },
            )
            Spacer(Modifier.height(12.dp))
            DropdownField(
                label = "Battery type and size",
                options = batteryTypes,
                selected = uiState.batteryType,
                onSelected = { viewModel.onBatteryTypeChanged(it) },
            )
            Spacer(Modifier.height(12.dp))
            RoundedTextField(
                value = uiState.brandPreference,
                onValueChange = { viewModel.onBrandPreferenceChanged(it) },
                placeholder = "Brand preference (e.g. Amaron, Exide)",
            )
            Spacer(Modifier.height(12.dp))
            DropdownField(
                label = "Budget",
                options = budgetRanges,
                selected = uiState.budget,
                onSelected = { viewModel.onBudgetChanged(it) },
            )
            Spacer(Modifier.height(80.dp))
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun BatteryServiceScreenPreview() {
    BatteryServiceScreen(onNavigateBack = {}, onBookService = {})
}
