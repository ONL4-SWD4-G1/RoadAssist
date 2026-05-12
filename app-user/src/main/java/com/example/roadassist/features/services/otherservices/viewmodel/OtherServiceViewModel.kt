package com.example.roadassist.features.services.otherservices.viewmodel

import androidx.lifecycle.ViewModel
import com.example.roadassist.features.services.otherservices.model.OtherServiceForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OtherServiceViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(OtherServiceForm())
    val uiState: StateFlow<OtherServiceForm> = _uiState.asStateFlow()

    fun onVehicleTypeChange(value: String) =
        _uiState.update { it.copy(vehicleType = value) }

    fun onManufacturerChange(value: String) =
        _uiState.update { it.copy(manufacturer = value) }

    fun onModelChange(value: String) =
        _uiState.update { it.copy(model = value) }

    fun onRegistrationNumChange(value: String) =
        _uiState.update { it.copy(registrationNum = value) }

    fun onProblemDescriptionChange(value: String) =
        _uiState.update { it.copy(problemDescription = value) }
}