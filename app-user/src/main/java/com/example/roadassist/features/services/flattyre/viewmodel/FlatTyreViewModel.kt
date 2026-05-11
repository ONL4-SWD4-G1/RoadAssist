package com.example.roadassist.features.services.flattyre.viewmodel

import androidx.lifecycle.ViewModel
import com.example.roadassist.fakedata.VehicleDetails
import com.example.roadassist.features.services.flattyre.model.FlatTyreUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FlatTyreViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(FlatTyreUiState())
    val uiState: StateFlow<FlatTyreUiState> = _uiState.asStateFlow()
    fun onVehicleDetailsChanged(v: VehicleDetails) {
        _uiState.update { it.copy(vehicleDetails = v) }
    }

    fun onVehicleMakeChanged(v: String) {
        _uiState.update { it.copy(vehicleMake = v) }
    }

    fun onTyreSizeChanged(v: String) {
        _uiState.update { it.copy(tyreSize = v) }
    }

    fun onSpareAvailableChanged(v: String) {
        _uiState.update { it.copy(spareAvailable = v) }
    }
}