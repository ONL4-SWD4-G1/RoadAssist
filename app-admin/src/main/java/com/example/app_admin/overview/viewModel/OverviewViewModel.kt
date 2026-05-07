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
            hourlyOrders = listOf(0f, 0f, 0f, 0f, 0f),
            todayRevenue = listOf(0f, 0f, 0f, 0f),
            yesterdayRevenue = listOf(0f, 0f, 0f, 0f)
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
            delay(500)

            val newData = when (periodIndex) {
                0 -> {
                    _uiState.update {
                        it.copy(
                            rating = "٤.٧ / ٥",
                            revenue = "١٢٥,٠٠٠ ر.س",
                            activeTechs = "٤٥ فني",
                            hourlyOrders = listOf(40f, 80f, 60f, 100f, 90f, 120f, 110f),
                            todayRevenue = listOf(80f, 90f, 70f, 100f),
                            yesterdayRevenue = listOf(60f, 70f, 65f, 80f),
                            isLoading = false
                        )
                    }
                }

                else -> {
                    _uiState.update {
                        it.copy(
                            rating = "٤.٨ / ٥",
                            revenue = "٤,٥٠٠ ر.س",
                            activeTechs = "١٢ فني",
                            hourlyOrders = listOf(10f, 25f, 15f, 40f, 30f, 55f, 45f),
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