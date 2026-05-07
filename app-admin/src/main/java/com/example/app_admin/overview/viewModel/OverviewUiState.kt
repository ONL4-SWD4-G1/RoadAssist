package com.example.app_admin.overview.viewModel

data class OverviewUiState(
    val isLoading: Boolean = false,
    val error: String? = null, val selectedPeriodIndex: Int = 2,
    val rating: String = "٠.٠",
    val revenue: String = "٠ ر.س",
    val activeTechs: String = "٠",
    val hourlyOrders: List<Float> = emptyList(),
    val todayRevenue: List<Float> = emptyList(),
    val yesterdayRevenue: List<Float> = emptyList()
)