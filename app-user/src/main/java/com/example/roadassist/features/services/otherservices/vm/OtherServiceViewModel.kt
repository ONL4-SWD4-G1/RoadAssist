package com.example.roadassist.features.services.otherservices.vm

import androidx.lifecycle.ViewModel
import com.example.roadassist.features.services.otherservices.model.OtherServiceForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OtherServiceViewModel : ViewModel() {

    private val _formState = MutableStateFlow(OtherServiceForm())
    val formState: StateFlow<OtherServiceForm> = _formState.asStateFlow()

    fun onVehicleTypeChange(value: String) =
        _formState.update { it.copy(vehicleType = value) }

    fun onManufacturerChange(value: String) =
        _formState.update { it.copy(manufacturer = value) }

    fun onModelChange(value: String) =
        _formState.update { it.copy(model = value) }

    fun onRegistrationNumChange(value: String) =
        _formState.update { it.copy(registrationNum = value) }

    fun onProblemDescriptionChange(value: String) =
        _formState.update { it.copy(problemDescription = value) }

    fun onBookService() {
        val form = _formState.value
    }
}