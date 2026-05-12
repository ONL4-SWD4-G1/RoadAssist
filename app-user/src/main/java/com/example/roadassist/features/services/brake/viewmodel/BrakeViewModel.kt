package com.example.roadassist.features.services.brake.viewmodel

import androidx.lifecycle.ViewModel
import com.example.roadassist.fakedata.VehicleDetails
import com.example.roadassist.features.services.brake.model.BrakeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BrakeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(BrakeUiState())
    val uiState: StateFlow<BrakeUiState> = _uiState.asStateFlow()
    fun onVehicleDetailsChanged(v: VehicleDetails) {
        _uiState.update { it.copy(vehicleDetails = v) }
    }

    fun onIssueSelected(v: String) {
        _uiState.update { it.copy(selectedIssue = v) }
    }

    fun onOtherDetailChanged(v: String) {
        _uiState.update { it.copy(otherDetail = v) }
    }
}