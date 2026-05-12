package com.example.roadassist.features.booking.payment.viewmodel

import androidx.lifecycle.ViewModel
import com.example.roadassist.fakedata.services
import com.example.roadassist.fakedata.technicians
import com.example.roadassist.features.booking.payment.model.PaymentUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PaymentViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PaymentUiState())
    val uiState: StateFlow<PaymentUiState> = _uiState.asStateFlow()

    fun loadData(serviceId: String, technicianId: String) {
        val service = services.find { it.id == serviceId } ?: services.first()
        val technician = technicians.find { it.id == technicianId } ?: technicians.first()
        _uiState.update { it.copy(service = service, technician = technician) }
    }

    fun onAgreedPriceChanged(price: String) {
        _uiState.update { it.copy(agreedPrice = price) }
    }

    fun onPaymentConfirmed() {
        _uiState.update { it.copy(showSuccess = true) }
    }
}
