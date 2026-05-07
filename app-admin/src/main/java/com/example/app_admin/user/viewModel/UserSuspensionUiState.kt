package com.example.app_admin.user.viewModel

data class UserSuspensionUiState(
    val isLoading: Boolean = false,
    val userName: String = "عبدالرحمن القحطاني",
    val totalOrders: String = "١٨",
    val falseComplaints: String = "١",
    val isPermanent: Boolean = false,
    val selectedDuration: String = "٣ أيام",
    val suspensionReason: String = "إساءة استخدام سياسة الاسترداد",
    val adminComment: String = "",
    val isSuccess: Boolean = false
)