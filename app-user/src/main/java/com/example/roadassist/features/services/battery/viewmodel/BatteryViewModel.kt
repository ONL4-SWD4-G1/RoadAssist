package com.example.roadassist.features.services.battery.viewmodel

import androidx.lifecycle.ViewModel
import com.example.roadassist.fakedata.VehicleDetails
import com.example.roadassist.features.services.battery.model.BatteryUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BatteryViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(BatteryUiState())
    val uiState: StateFlow<BatteryUiState> = _uiState.asStateFlow()
    fun onVehicleDetailsChanged(v: VehicleDetails) {
        _uiState.update { it.copy(vehicleDetails = v) }
    }

    fun onBatteryConditionChanged(v: String) {
        _uiState.update { it.copy(batteryCondition = v) }
    }

    fun onBatteryTypeChanged(v: String) {
        _uiState.update { it.copy(batteryType = v) }
    }

    fun onBrandPreferenceChanged(v: String) {
        _uiState.update { it.copy(brandPreference = v) }
    }

    fun onBudgetChanged(v: String) {
        _uiState.update { it.copy(budget = v) }
    }
}