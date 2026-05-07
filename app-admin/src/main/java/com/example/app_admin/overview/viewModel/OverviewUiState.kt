package com.example.app_admin.overview.viewModel

data class OverviewUiState(
    val isLoading: Boolean = false,
    val selectedPeriodIndex: Int = 2,
    val hourlyOrders: List<Float> = emptyList(),
    val chartLabels: List<String> = emptyList(), // For the Line Chart
    val revenueBarLabels: List<String> = emptyList(), // Added for the Bar Chart
    val error: String? = null,
    val rating: String = "٠.٠",
    val revenue: String = "٠ ر.س",
    val activeTechs: String = "٠",
    val todayRevenue: List<Float> = emptyList(),
    val yesterdayRevenue: List<Float> = emptyList()
)