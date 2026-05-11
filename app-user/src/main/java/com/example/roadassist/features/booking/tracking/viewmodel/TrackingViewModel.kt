package com.example.roadassist.features.booking.tracking.viewmodel

import androidx.lifecycle.ViewModel
import com.example.roadassist.fakedata.technicians
import com.example.roadassist.features.booking.tracking.model.TrackingUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TrackingViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TrackingUiState())
    val uiState: StateFlow<TrackingUiState> = _uiState.asStateFlow()

    fun loadTechnician(technicianId: String) {
        val tech = technicians.find { it.id == technicianId } ?: technicians.first()
        _uiState.update { it.copy(technician = tech) }
    }
}
