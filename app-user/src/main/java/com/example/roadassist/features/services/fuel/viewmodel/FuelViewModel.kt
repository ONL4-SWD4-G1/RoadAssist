package com.example.roadassist.features.services.fuel.viewmodel

import androidx.lifecycle.ViewModel
import com.example.roadassist.fakedata.VehicleDetails
import com.example.roadassist.features.services.fuel.model.FuelUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FuelViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(FuelUiState())
    val uiState: StateFlow<FuelUiState> = _uiState.asStateFlow()
    fun onVehicleDetailsChanged(v: VehicleDetails) {
        _uiState.update { it.copy(vehicleDetails = v) }
    }

    fun onFuelTypeChanged(v: String) {
        _uiState.update { it.copy(fuelType = v) }
    }

    fun onFuelAmountChanged(v: String) {
        _uiState.update { it.copy(fuelAmount = v) }
    }
}