package com.example.app_admin.technicians.viewModel

data class TechnicianDeductionUiState(
    val techName: String = "محمد سامي",
    val techSpecialty: String = "فني إطارات متخصص",
    val currentPendingBalance: Double = 850.0,
    val totalEarnings: Double = 4250.0,
    val deductionAmount: String = "50",
    val deductionReason: String = "تأخر في الوصول",
    val adminNote: String = "",
    val isLoading: Boolean = false
) {
    val expectedBalance: Double get() = currentPendingBalance - (deductionAmount.toDoubleOrNull() ?: 0.0)
}