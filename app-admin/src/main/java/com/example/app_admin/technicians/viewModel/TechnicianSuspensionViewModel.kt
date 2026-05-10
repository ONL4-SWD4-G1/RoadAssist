package com.example.app_admin.technicians.viewModel

import androidx.lifecycle.ViewModel
import com.example.app_admin.sampleTechnicians
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TechnicianSuspensionViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TechnicianSuspensionUiState())
    val uiState = _uiState.asStateFlow()


    fun initialize(techId: Int) {
        val technician = sampleTechnicians.find { it.id == techId }
        technician?.let { tech ->
            _uiState.update {
                it.copy(
                    techName = tech.name,
                    techSpecialty = tech.specialty,
                    completedJobs = tech.completedJobs,
                    rating = tech.rating
                )
            }
        }
    }

    fun updateSuspensionType(permanent: Boolean) {
        _uiState.update { it.copy(isPermanent = permanent) }
    }

    fun updateDuration(duration: String) {
        _uiState.update { it.copy(selectedDuration = duration) }
    }

    fun updateReason(reason: String) {
        _uiState.update { it.copy(selectedReason = reason) }
    }

    fun updateNote(note: String) {
        _uiState.update { it.copy(adminNote = note) }
    }

    fun confirmSuspension() {
    }
}