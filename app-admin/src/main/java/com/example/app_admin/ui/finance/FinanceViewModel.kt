package com.example.app_admin.ui.finance

import androidx.lifecycle.ViewModel
import com.example.app_admin.data.model.TransactionData
import com.example.app_admin.data.model.transactions as sampleTransactions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class FinanceUiState(
    val transactions: List<TransactionData> = sampleTransactions,
    val selectedTab: Int = 0,
    val totalRevenue: String = "34,800",
    val todayRevenue: String = "1,250",
    val totalCommissions: String = "5,420",
    val pendingPayments: String = "2,100"
)

class FinanceViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(FinanceUiState())
    val uiState: StateFlow<FinanceUiState> = _uiState.asStateFlow()

    fun selectTab(index: Int) {
        _uiState.update { it.copy(selectedTab = index) }
    }
}