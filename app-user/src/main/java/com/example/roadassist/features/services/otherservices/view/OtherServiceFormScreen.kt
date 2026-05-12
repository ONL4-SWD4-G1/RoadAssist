package com.example.roadassist.features.services.otherservices.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roadassist.features.home.notification.view.TopBarWithBack
import com.example.roadassist.features.services.otherservices.vm.OtherServiceViewModel
import com.example.roadassist.theme.Background
import com.example.roadassist.theme.NavyBlue
import com.example.roadassist.theme.OrangeAccent
import com.example.roadassist.theme.TextGray
import com.example.roadassist.theme.TextPrimary

@Composable
fun OtherServiceFormScreen(
    onBackClick: () -> Unit = {},
    viewModel: OtherServiceViewModel = viewModel()
) {
    val form by viewModel.formState.collectAsState()

    Scaffold(containerColor = Background) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            TopBarWithBack(title = "Other Service", onBackClick = onBackClick)

            Spacer(modifier = Modifier.height(16.dp))

            // ── Vehicle Details Card ──────────────────────────────────
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = com.example.roadassist.theme.White),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Vehicle details", fontWeight = FontWeight.Bold,
                        fontSize = 16.sp, color = TextPrimary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    FormField(
                        value = form.vehicleType,
                        onValueChange = viewModel::onVehicleTypeChange,
                        placeholder = "Vehicle Type"
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    FormField(
                        value = form.manufacturer,
                        onValueChange = viewModel::onManufacturerChange,
                        placeholder = "Manufacturer"
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    FormField(
                        value = form.model,
                        onValueChange = viewModel::onModelChange,
                        placeholder = "Model"
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    FormField(
                        value = form.registrationNum,
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
                    Text("Please describe the problem of vehicle in detail",
                        fontSize = 14.sp, color = TextPrimary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = form.problemDescription,
                        onValueChange = viewModel::onProblemDescriptionChange,
                        placeholder = { Text("Describe issue here...", color = TextGray, fontSize = 13.sp) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = NavyBlue,
                            unfocusedBorderColor = Color(0xFFE0E0E0)
                        ),
                        maxLines = 5
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = viewModel::onBookService,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(52.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = OrangeAccent)
            ) {
                Text("Book Service", fontSize = 16.sp,
                    fontWeight = FontWeight.Bold, color = com.example.roadassist.theme.White
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

// ── Reusable UI component — no logic, stays in view layer ────────────
@Composable
fun FormField(value: String, onValueChange: (String) -> Unit, placeholder: String) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = TextGray, fontSize = 13.sp) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = NavyBlue,
            unfocusedBorderColor = Color(0xFFE0E0E0),
            focusedContainerColor = com.example.roadassist.theme.White,
            unfocusedContainerColor = com.example.roadassist.theme.White
        ),
        singleLine = true
    )
}