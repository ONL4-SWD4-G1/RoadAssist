package com.example.roadassist.features.services.towing.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roadassist.components.DropdownField
import com.example.roadassist.components.PrimaryButton
import com.example.roadassist.components.RoundedTextField
import com.example.roadassist.components.SectionHeader
import com.example.roadassist.components.UserTopBar
import com.example.roadassist.components.VehicleDetailsSection
import com.example.roadassist.features.services.towing.viewmodel.TowingViewModel
import com.example.roadassist.theme.NavyBlue

private val breakdownReasons =
    listOf("Engine failure", "Flat tyre", "Accident", "Battery dead", "Out of fuel", "Other")
private val vehicleConditions = listOf("Drivable", "Not drivable", "Severely damaged")

@Composable
fun TowingServiceScreen(
    onNavigateBack: () -> Unit,
    onBookService: () -> Unit,
    onSelectMapClicked: () -> Unit,
    viewModel: TowingViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { UserTopBar("Towing service", onNavigateBack) },
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
            SectionHeader("Vehicle Issue")
            Spacer(Modifier.height(12.dp))
            DropdownField(
                label = "Reason for breakdown",
                options = breakdownReasons,
                selected = uiState.breakdownReason,
                onSelected = { viewModel.onBreakdownReasonChanged(it) },
            )
            Spacer(Modifier.height(12.dp))
            DropdownField(
                label = "Condition of your vehicle",
                options = vehicleConditions,
                selected = uiState.vehicleCondition,
                onSelected = { viewModel.onVehicleConditionChanged(it) },
            )
            Spacer(Modifier.height(24.dp))
            SectionHeader("Drop by")
            Spacer(Modifier.height(12.dp))
            RoundedTextField(
                value = uiState.destination,
                onValueChange = { viewModel.onDestinationChanged(it) },
                placeholder = "Enter your preferred destination",
            )
            Spacer(Modifier.height(12.dp))
            OutlinedButton(
                onClick = onSelectMapClicked,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = NavyBlue),
                border = BorderStroke(1.dp, NavyBlue),
            ) {
                Icon(Icons.Filled.NearMe, null, tint = NavyBlue)
                Spacer(Modifier.width(8.dp))
                Text("Select on map", color = NavyBlue, fontWeight = FontWeight.SemiBold)
            }
            Spacer(Modifier.height(80.dp))
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TowingServiceScreenPreview() {
    TowingServiceScreen(onNavigateBack = {}, onBookService = {}, onSelectMapClicked = {})
}
