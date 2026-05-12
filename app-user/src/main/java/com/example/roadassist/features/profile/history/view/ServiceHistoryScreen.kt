package com.example.roadassist.features.profile.history.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.example.roadassist.components.UserTopBar
import com.example.roadassist.features.profile.history.viewmodel.ServiceHistoryViewModel
import com.example.roadassist.theme.OrangeButton
import com.example.roadassist.theme.TextPrimary
import com.example.roadassist.theme.White


@Composable
fun ServiceHistoryScreen(
    onNavigateBack: () -> Unit,
    viewModel: ServiceHistoryViewModel = viewModel()
) {
    Scaffold(
        topBar = { UserTopBar("Service History", onNavigateBack) },
        containerColor = White,
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp)
            ) {
                Text(
                    "Date",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    "Service Done",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.weight(2f)
                )
            }

            val uiState by viewModel.uiState.collectAsState()
            uiState.items.forEach { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(1.dp),
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            item.date,
                            fontSize = 13.sp,
                            color = TextPrimary,
                            modifier = Modifier.width(100.dp)
                        )
                        Column(modifier = Modifier.weight(1f)) {
                            Text(item.service, fontSize = 14.sp)
                        }
                        TextButton(onClick = {}) {
                            Text("View", color = OrangeButton, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ServiceHistoryScreenPreview() {
    ServiceHistoryScreen(
        {}
    )
}