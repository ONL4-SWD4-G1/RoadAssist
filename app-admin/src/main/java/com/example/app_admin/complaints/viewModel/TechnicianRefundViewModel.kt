package com.example.app_admin.complaints.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TechnicianRefundViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RefundUiState())
    val uiState = _uiState.asStateFlow()

    fun initialize(orderId: String) {
        // In a real app, fetch from repository. For now, we simulate.
        _uiState.update {
            it.copy(
                orderNumber = orderId,
                servicePrice = 150.0,
                commissionRate = 0.15
            )
        }
    }

    fun setRefundType(isFull: Boolean) {
        _uiState.update {
            it.copy(
                isFullRefund = isFull,
                // If full, set amount to totalPaid, else default partial
                refundAmount = if (isFull) it.totalPaid.toString() else "50.00"
            )
        }
    }

    fun updateAmount(amount: String) {
        // 3. Logic to prevent typing more than the total paid (Validation)
        val numericAmount = amount.toDoubleOrNull() ?: 0.0
        if (!uiState.value.isFullRefund && numericAmount <= uiState.value.totalPaid) {
            _uiState.update { it.copy(refundAmount = amount) }
        }
    }

    fun updateReason(reason: String) {
        _uiState.update { it.copy(refundReason = reason) }
    }

    fun updateNotes(notes: String) {
        _uiState.update { it.copy(adminNotes = notes) }
    }

    // 2. Logic to handle the confirm button click
    fun confirmRefund(onSuccess: () -> Unit) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            // Simulate Network Call
            delay(1500)

            _uiState.update { it.copy(isLoading = false) }
            onSuccess()
        }
    }
}