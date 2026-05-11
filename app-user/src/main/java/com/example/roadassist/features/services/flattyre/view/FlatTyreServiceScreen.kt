package com.example.roadassist.features.services.flattyre.view

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
import com.example.roadassist.features.services.flattyre.viewmodel.FlatTyreViewModel

private val tyreSizes =
    listOf("155/70 R13", "175/65 R14", "185/65 R15", "195/65 R15", "205/55 R16", "Other")
private val spareTyreOptions = listOf("Yes", "No", "Not sure")

@Composable
fun FlatTyreServiceScreen(
    onNavigateBack: () -> Unit,
    onBookService: () -> Unit,
    viewModel: FlatTyreViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { UserTopBar("Flat tyre", onNavigateBack) },
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
            SectionHeader("Tyre requirements")
            Spacer(Modifier.height(12.dp))
            RoundedTextField(
                value = uiState.vehicleMake,
                onValueChange = { viewModel.onVehicleMakeChanged(it) },
                placeholder = "Vehicle make",
            )
            Spacer(Modifier.height(12.dp))
            DropdownField(
                label = "Tyre size",
                options = tyreSizes,
                selected = uiState.tyreSize,
                onSelected = { viewModel.onTyreSizeChanged(it) },
            )
            Spacer(Modifier.height(12.dp))
            DropdownField(
                label = "Whether spare tyre available",
                options = spareTyreOptions,
                selected = uiState.spareAvailable,
                onSelected = { viewModel.onSpareAvailableChanged(it) },
            )
            Spacer(Modifier.height(80.dp))
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun FlatTyreServiceScreenPreview() {
    FlatTyreServiceScreen(onNavigateBack = {}, onBookService = {})
}
