package com.example.app_admin.finance.viewModel

import com.example.app_admin.finance.model.TransactionData

data class FinanceUiState(
    val transactions: List<TransactionData> = com.example.app_admin.transactions,
    val selectedTab: Int = 0,
    val totalRevenue: String = "34,800",
    val todayRevenue: String = "1,250",
    val totalCommissions: String = "5,420",
    val pendingPayments: String = "2,100"
)