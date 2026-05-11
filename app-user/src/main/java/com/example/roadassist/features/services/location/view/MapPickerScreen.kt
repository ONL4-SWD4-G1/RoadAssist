package com.example.roadassist.features.services.location.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roadassist.components.PrimaryButton
import com.example.roadassist.components.SearchBar
import com.example.roadassist.components.UserTopBar
import com.example.roadassist.features.services.location.viewmodel.LocationViewModel
import com.example.roadassist.theme.White
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapPickerScreen(
    onNavigateBack: () -> Unit,
    onLocationSelected: (LatLng) -> Unit,
    viewModel: LocationViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(uiState.pinnedLat, uiState.pinnedLng), 12f
        )
    }

    // Whenever the camera stops moving, sync the center position to the ViewModel
    LaunchedEffect(cameraPositionState) {
        snapshotFlow { cameraPositionState.isMoving }
            .collect { isMoving ->
                if (!isMoving) {
                    val target = cameraPositionState.position.target
                    viewModel.onLocationPinned(target.latitude, target.longitude)
                }
            }
    }

    Scaffold(
        topBar = { UserTopBar("Select on map", onNavigateBack) },
        containerColor = White,
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White)
                    .padding(horizontal = 20.dp, vertical = 16.dp),
            ) {
                PrimaryButton(
                    text = "Done",
                    onClick = {
                        onLocationSelected(
                            LatLng(uiState.pinnedLat, uiState.pinnedLng)
                        )
                    },
                )
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            SearchBar(
                value = uiState.searchQuery,
                onValueChange = { viewModel.onSearchQueryChanged(it) },
            )
            Box(modifier = Modifier.weight(1f)) {
                GoogleMap(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    cameraPositionState = cameraPositionState,
                )
                // Static center pin — user drags the map underneath it
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.Center),
                )
            }
            Spacer(Modifier.height(12.dp))
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MapPickerScreenPreview() {
    MapPickerScreen(onNavigateBack = {}, onLocationSelected = {})
}
