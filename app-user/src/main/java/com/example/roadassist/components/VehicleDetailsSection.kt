package com.example.roadassist.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.roadassist.fakedata.VehicleDetails


@Composable
fun VehicleDetailsSection(
    details: VehicleDetails,
    onDetailsChange: (VehicleDetails) -> Unit,
) {
    SectionHeader("Vehicle details")
    Spacer(Modifier.height(8.dp))
    RoundedTextField(
        value = details.vehicleType,
        onValueChange = { onDetailsChange(details.copy(vehicleType = it)) },
        placeholder = "Vehicle Type",
    )
    Spacer(Modifier.height(12.dp))
    RoundedTextField(
        value = details.manufacturer,
        onValueChange = { onDetailsChange(details.copy(manufacturer = it)) },
        placeholder = "Manufacturer",
    )
    Spacer(Modifier.height(12.dp))
    RoundedTextField(
        value = details.model,
        onValueChange = { onDetailsChange(details.copy(model = it)) },
        placeholder = "Model",
    )
    Spacer(Modifier.height(12.dp))
    RoundedTextField(
        value = details.registration,
        onValueChange = { onDetailsChange(details.copy(registration = it)) },
        placeholder = "Enter Registration number",
    )
}