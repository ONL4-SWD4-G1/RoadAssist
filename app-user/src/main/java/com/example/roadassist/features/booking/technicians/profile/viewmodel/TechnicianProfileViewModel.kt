package com.example.roadassist.features.booking.technicians.profile.viewmodel

import androidx.lifecycle.ViewModel
import com.example.roadassist.fakedata.technicians
import com.example.roadassist.features.booking.technicians.profile.model.TechnicianProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TechnicianProfileViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TechnicianProfileUiState())
    val uiState: StateFlow<TechnicianProfileUiState> = _uiState.asStateFlow()

    fun loadTechnician(id: String) {
        val tech = technicians.find { it.id == id } ?: technicians.first()
        _uiState.update { it.copy(technician = tech) }
    }

    fun onConfirmClicked() {
        _uiState.update { it.copy(showBookingSuccess = true) }
    }

    fun onBookingSuccessDismissed() {
        _uiState.update { it.copy(showBookingSuccess = false) }
    }
}
