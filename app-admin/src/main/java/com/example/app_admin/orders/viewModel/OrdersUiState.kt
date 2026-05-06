package com.example.app_admin.orders.viewModel

import com.example.app_admin.orders.model.Order

data class OrdersUiState(
    val orders: List<Order> = emptyList(),
    val selectedTabIndex: Int = 0,
    val isLoading: Boolean = false,
    val tabs: List<String> = listOf("الكل", "النشطة", "الملغاة", "المكتملة", "المتنازع عليها")
)