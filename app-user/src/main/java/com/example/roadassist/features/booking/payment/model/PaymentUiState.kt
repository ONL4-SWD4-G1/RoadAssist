package com.example.roadassist.features.booking.payment.model

import com.example.roadassist.fakedata.ServiceItem
import com.example.roadassist.fakedata.Technician

data class PaymentUiState(
    val service: ServiceItem? = null,
    val technician: Technician? = null,
    val agreedPrice: String = "",
    val showSuccess: Boolean = false,
)
