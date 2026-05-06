package com.example.app_admin.orders.viewModel


import androidx.lifecycle.ViewModel
import com.example.app_admin.orders.model.OrderStatus
import com.example.app_admin.sampleOrders
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OrdersViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(OrdersUiState())
    val uiState: StateFlow<OrdersUiState> = _uiState.asStateFlow()

    init {
        loadOrders()
    }

    private fun loadOrders() {
        _uiState.update { it.copy(orders = sampleOrders, isLoading = false) }
    }

    fun onTabSelected(index: Int) {
        _uiState.update { currentState ->
            val filteredOrders = when (index) {
                0 -> sampleOrders // "الكل"
                1 -> sampleOrders.filter { it.orderStatus == OrderStatus.IN_PROGRESS || it.orderStatus == OrderStatus.NEW_REQUEST } // "النشطة"
                2 -> sampleOrders.filter { it.orderStatus == OrderStatus.CANCELLED } // "الملغاة"
                3 -> sampleOrders.filter { it.orderStatus == OrderStatus.COMPLETED } // "المكتملة"
                else -> sampleOrders // Handle others
            }
            currentState.copy(
                selectedTabIndex = index,
                orders = filteredOrders
            )
        }
    }
}