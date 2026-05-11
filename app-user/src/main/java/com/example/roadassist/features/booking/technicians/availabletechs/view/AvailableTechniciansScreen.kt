package com.example.roadassist.features.booking.technicians.availabletechs.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roadassist.components.PrimaryButton
import com.example.roadassist.components.SearchBar
import com.example.roadassist.components.UserTopBar
import com.example.roadassist.fakedata.Technician
import com.example.roadassist.features.booking.technicians.availabletechs.viewmodel.AvailableTechniciansViewModel
import com.example.roadassist.theme.CardBg
import com.example.roadassist.theme.GreenSuccess
import com.example.roadassist.theme.OrangeButton
import com.example.roadassist.theme.RoadAssistTheme

@Composable
fun AvailableTechniciansScreen(
    onSelectTechnician: (String) -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: AvailableTechniciansViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { UserTopBar("Available Technicians", onNavigateBack) },
        containerColor = Color.White,
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            SearchBar(
                value = uiState.searchQuery,
                onValueChange = { viewModel.onSearchQueryChanged(it) },
                placeHolder = "Search by specialty or name",
            )
            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(start = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                uiState.filters.forEach { filter ->
                    FilterChip(
                        selected = uiState.selectedFilter == filter,
                        onClick = { viewModel.onFilterSelected(filter) },
                        label = { Text(filter, fontSize = 14.sp) },
                        border = null,
                        shape = RoundedCornerShape(24.dp),
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = OrangeButton,
                            selectedLabelColor = Color.White,
                            containerColor = CardBg,
                        ),
                        trailingIcon = {
                            if (filter != uiState.filters.first()) {
                                Icon(
                                    Icons.Outlined.KeyboardArrowDown,
                                    null,
                                    modifier = Modifier.size(12.dp)
                                )
                            }
                        },
                    )
                }
            }
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(16.dp),
            ) {
                items(uiState.filteredTechnicians) { tech ->
                    TechnicianCard(tech = tech, onSelect = { onSelectTechnician(tech.id) })
                }
            }
        }
    }
}

@Composable
private fun TechnicianCard(tech: Technician, onSelect: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(12.dp))
                ) {
                    Image(painterResource(tech.image), null, modifier = Modifier.fillMaxSize())
                }
                Spacer(Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(tech.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(
                        tech.role,
                        color = OrangeButton,
                        fontWeight = FontWeight.Medium,
                        fontSize = 13.sp
                    )
                    Spacer(Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Outlined.LocationOn,
                            null,
                            tint = Color(0xFF6B7280),
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            " ${tech.distanceKm} km away",
                            fontSize = 12.sp,
                            color = Color(0xFF6B7280)
                        )
                        Spacer(Modifier.width(8.dp))
                        Icon(
                            Icons.Outlined.Schedule,
                            null,
                            tint = OrangeButton,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            " ${tech.etaMinutes} mins arrival",
                            fontSize = 12.sp,
                            color = GreenSuccess,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFFFFBEB))
                        .padding(horizontal = 4.dp, vertical = 4.dp),
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Star,
                            null,
                            tint = Color(0xFFF59E0B),
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(Modifier.width(2.dp))
                        Text("${tech.rating}", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    }
                }
            }
            Spacer(Modifier.height(12.dp))
            PrimaryButton("Select Technician", onSelect)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AvailableTechniciansPreview() {
    RoadAssistTheme { AvailableTechniciansScreen(onSelectTechnician = {}, onNavigateBack = {}) }
}
