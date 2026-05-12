package com.example.app_admin.overview.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        OverviewUiState(
            hourlyOrders = listOf(15f, 22f, 18f, 35f, 50f, 42f, 38f, 45f),
            chartLabels = listOf("12م", "1م", "2م", "3م", "4م", "5م", "6م", "7م"),
            revenueBarLabels = listOf("الصباح", "الظهر", "المساء", "الليل"),
            todayRevenue = listOf(40f, 60f, 85f, 30f),
            yesterdayRevenue = listOf(30f, 45f, 70f, 25f),
            selectedPeriodIndex = 2
        )
    )
    val uiState: StateFlow<OverviewUiState> = _uiState.asStateFlow()

    init {
        loadDashboardData(2)
    }

    fun onPeriodSelected(index: Int) {
        if (_uiState.value.selectedPeriodIndex == index) return

        _uiState.update { it.copy(selectedPeriodIndex = index, isLoading = true) }

        loadDashboardData(index)
    }

    private fun loadDashboardData(periodIndex: Int) {
        viewModelScope.launch {
            delay(500) // Simulate network delay

            _uiState.update { currentState ->
                when (periodIndex) {
                    0 -> { // Monthly View
                        currentState.copy(
                            rating = "٤.٧ / ٥",
                            revenue = "١٢٥,٠٠٠ ر.س",
                            activeTechs = "٤٥ فني",
                            hourlyOrders = listOf(100f, 150f, 120f, 200f, 180f, 250f, 220f, 300f),
                            chartLabels = listOf("يناير", "فبراير", "مارس", "أبريل", "مايو", "يونيو", "يوليو", "أغسطس"),
                            revenueBarLabels = listOf("Q1", "Q2", "Q3", "Q4"),
                            todayRevenue = listOf(80f, 90f, 70f, 100f),
                            yesterdayRevenue = listOf(60f, 70f, 65f, 80f),
                            isLoading = false
                        )
                    }

                    1 -> { // Weekly View
                        currentState.copy(
                            rating = "٤.٩ / ٥",
                            revenue = "٣٢,٠٠٠ ر.س",
                            activeTechs = "٢٨ فني",
                            hourlyOrders = listOf(30f, 45f, 60f, 20f, 80f, 90f, 50f, 70f),
                            chartLabels = listOf("سبت", "أحد", "اثنين", "ثلاثاء", "أربعاء", "خميس", "جمعة", "سبت"),
                            revenueBarLabels = listOf("سبت", "أحد", "اثنين", "ثلاثاء"),
                            todayRevenue = listOf(50f, 70f, 60f, 40f),
                            yesterdayRevenue = listOf(40f, 50f, 45f, 30f),
                            isLoading = false
                        )
                    }

                    else -> { // Daily View (Default)
                        currentState.copy(
                            rating = "٤.٨ / ٥",
                            revenue = "٤,٥٠٠ ر.س",
                            activeTechs = "١٢ فني",
                            hourlyOrders = listOf(15f, 22f, 18f, 35f, 50f, 42f, 38f, 45f),
                            chartLabels = listOf("12م", "1م", "2م", "3م", "4م", "5م", "6م", "7م"),
                            revenueBarLabels = listOf("الصباح", "الظهر", "المساء", "الليل"),
                            todayRevenue = listOf(40f, 60f, 85f, 30f),
                            yesterdayRevenue = listOf(30f, 45f, 70f, 25f),
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}