package com.example.app_admin.complaints.viewModel

data class RefundUiState(
    val orderNumber: String = "ORD-5521",
    val servicePrice: Double = 150.0,
    val commissionRate: Double = 0.15,
    val isFullRefund: Boolean = false,
    val refundAmount: String = "50.00",
    val refundReason: String = "سعر مبالغ فيه / خطأ في التسعير",
    val adminNotes: String = "",
    val isLoading: Boolean = false
) {
    val commissionValue: Double get() = servicePrice * commissionRate
    val totalPaid: Double get() = servicePrice + commissionValue

    val clientReturn: Double get() = refundAmount.toDoubleOrNull() ?: 0.0
    val techDeduction: Double get() = clientReturn * (1 + commissionRate)
}