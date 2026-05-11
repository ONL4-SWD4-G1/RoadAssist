package com.example.roadassist.features.services.location.viewmodel

import androidx.lifecycle.ViewModel
import com.example.roadassist.features.services.location.model.LocationUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LocationViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LocationUiState())
    val uiState: StateFlow<LocationUiState> = _uiState.asStateFlow()
    fun onLandmarkChanged(v: String) {
        _uiState.update { it.copy(landmark = v) }
    }

    fun onSearchQueryChanged(v: String) {
        _uiState.update { it.copy(searchQuery = v) }
    }

    fun onLocationPinned(lat: Double, lng: Double) {
        _uiState.update { it.copy(pinnedLat = lat, pinnedLng = lng) }
    }
}