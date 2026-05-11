package com.example.roadassist.features.booking.payment.model

import com.example.roadassist.fakedata.ServiceItem

data class PaymentUiState(
    val service: ServiceItem? = null,
    val agreedPrice: String = "",
    val showSuccess: Boolean = false,
)
