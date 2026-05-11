package com.example.roadassist.features.services.brake.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roadassist.components.PrimaryButton
import com.example.roadassist.components.RoundedTextField
import com.example.roadassist.components.SectionHeader
import com.example.roadassist.components.UserTopBar
import com.example.roadassist.components.VehicleDetailsSection
import com.example.roadassist.features.services.brake.viewmodel.BrakeViewModel
import com.example.roadassist.theme.TextPrimary

private val brakeIssues = listOf("Unusual noise", "Vibration", "Brake warning light", "Other")

@Composable
fun BrakeServiceScreen(
    onNavigateBack: () -> Unit,
    onBookService: () -> Unit,
    viewModel: BrakeViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { UserTopBar("Brake", onNavigateBack) },
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
            SectionHeader("Select the issue facing in brake")
            Spacer(Modifier.height(16.dp))
            brakeIssues.forEach { issue ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(36.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    RadioButton(
                        selected = uiState.selectedIssue == issue,
                        onClick = { viewModel.onIssueSelected(issue) },
                    )
                    Text(issue, fontSize = 16.sp, color = TextPrimary)
                }
                if (issue == "Other" && uiState.selectedIssue == "Other") {
                    Spacer(Modifier.height(4.dp))
                    RoundedTextField(
                        value = uiState.otherDetail,
                        onValueChange = { viewModel.onOtherDetailChanged(it) },
                        placeholder = "Enter in detail",
                        maxLines = 4,
                        modifier = Modifier
                            .padding(start = 36.dp)
                            .height(90.dp),
                    )
                }
            }
            Spacer(Modifier.height(80.dp))
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun BrakeServiceScreenPreview() {
    BrakeServiceScreen(onNavigateBack = {}, onBookService = {})
}
