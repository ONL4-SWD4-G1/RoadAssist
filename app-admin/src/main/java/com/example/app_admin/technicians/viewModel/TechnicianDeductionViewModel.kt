package com.example.app_admin.technicians.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TechnicianDeductionViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TechnicianDeductionUiState())
    val uiState = _uiState.asStateFlow()

    fun initialize(techId: Int) {
        // Logic to fetch tech data by ID would go here
    }

    fun updateAmount(amount: String) {
        if (amount.all { it.isDigit() }) {
            _uiState.update { it.copy(deductionAmount = amount) }
        }
    }

    fun updateReason(reason: String) {
        _uiState.update { it.copy(deductionReason = reason) }
    }

    fun updateNote(note: String) {
        _uiState.update { it.copy(adminNote = note) }
    }

    fun confirmDeduction() {
        _uiState.update { it.copy(isLoading = true) }
        // API Call simulation...
    }
}