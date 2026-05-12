package com.example.roadassist.features.services.towing.viewmodel

import androidx.lifecycle.ViewModel
import com.example.roadassist.fakedata.VehicleDetails
import com.example.roadassist.features.services.towing.model.TowingUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TowingViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TowingUiState())
    val uiState: StateFlow<TowingUiState> = _uiState.asStateFlow()
    fun onVehicleDetailsChanged(v: VehicleDetails) {
        _uiState.update { it.copy(vehicleDetails = v) }
    }

    fun onBreakdownReasonChanged(v: String) {
        _uiState.update { it.copy(breakdownReason = v) }
    }

    fun onVehicleConditionChanged(v: String) {
        _uiState.update { it.copy(vehicleCondition = v) }
    }

    fun onDestinationChanged(v: String) {
        _uiState.update { it.copy(destination = v) }
    }
}