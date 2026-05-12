package com.example.roadassist.features.services.location.view

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.MyLocation
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
import com.example.roadassist.components.UnderlineTextField
import com.example.roadassist.components.UserTopBar
import com.example.roadassist.features.services.location.viewmodel.LocationViewModel
import com.example.roadassist.theme.NavyBlue
import com.example.roadassist.theme.OrangeButton
import com.example.roadassist.theme.TextSecondary
import com.example.roadassist.theme.White

@Composable
fun LocationConfirmationScreen(
    onNavigateBack: () -> Unit,
    onConfirm: () -> Unit,
    viewModel: LocationViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { UserTopBar("Location Confirmation", onNavigateBack) },
        containerColor = White,
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
        ) {
            Text("Confirm your location", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .background(Color(0xFFE8EAF6), RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center,
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        Icons.Outlined.LocationOn,
                        null,
                        tint = OrangeButton,
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(Modifier.height(8.dp))
                    Text("Your location pin", color = TextSecondary, fontSize = 13.sp)
                }
            }
            Spacer(Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Outlined.MyLocation,
                    null,
                    tint = NavyBlue,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text("Detected: 42, Thiru Street, Metur", fontSize = 14.sp, color = TextSecondary)
            }
            Spacer(Modifier.height(20.dp))
            UnderlineTextField(
                value = uiState.landmark,
                onValueChange = { viewModel.onLandmarkChanged(it) },
                placeholder = "Landmark (optional)",
            )
            Spacer(Modifier.weight(1f))
            PrimaryButton("Confirm", onConfirm)
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LocationConfirmationScreenPreview() {
    LocationConfirmationScreen(onNavigateBack = {}, onConfirm = {})
}
