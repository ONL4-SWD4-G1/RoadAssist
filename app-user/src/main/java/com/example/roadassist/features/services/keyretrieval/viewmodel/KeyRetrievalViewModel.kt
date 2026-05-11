package com.example.roadassist.features.services.keyretrieval.viewmodel

import androidx.lifecycle.ViewModel
import com.example.roadassist.fakedata.VehicleDetails
import com.example.roadassist.features.services.keyretrieval.model.KeyRetrievalUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class KeyRetrievalViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(KeyRetrievalUiState())
    val uiState: StateFlow<KeyRetrievalUiState> = _uiState.asStateFlow()
    fun onVehicleDetailsChanged(v: VehicleDetails) {
        _uiState.update { it.copy(vehicleDetails = v) }
    }

    fun onLockManufacturerChanged(v: String) {
        _uiState.update { it.copy(lockManufacturer = v) }
    }

    fun onOwnershipUploaded() {
        _uiState.update { it.copy(ownershipUploaded = true) }
    }

    fun onSpecialInstructionsChanged(v: String) {
        _uiState.update { it.copy(specialInstructions = v) }
    }
}