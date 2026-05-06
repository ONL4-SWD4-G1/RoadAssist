package com.example.app_admin.orders.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.app_admin.orders.components.OrderCard
import com.example.app_admin.orders.components.ScrollableSummaryRow
import com.example.app_admin.orders.components.SectionHeader
import com.example.app_admin.orders.viewModel.OrdersViewModel
import com.example.app_admin.shared.RoadAssistTabRow
import com.example.app_admin.shared.RoadAssistTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersScreen(
    viewModel: OrdersViewModel, // Injecting ViewModel
    onMenuClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {},
    onFilterClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold(
            topBar = {
                RoadAssistTopAppBar(
                    title = "إدارة الطلبات",
                    navigationIcon = {
                        IconButton(onClick = onMenuClick) {
                            Icon(Icons.Default.Menu, contentDescription = null)
                        }
                    },
                    actions = {
                        IconButton(onClick = onNotificationClick) {
                            Icon(Icons.Default.NotificationsNone, contentDescription = null)
                        }
                    },
                    bottomContent = {
                        RoadAssistTabRow(
                            tabs = uiState.tabs,
                            selectedTabIndex = uiState.selectedTabIndex,
                            onTabSelected = { viewModel.onTabSelected(it) },
                            isScrollable = true
                        )
                    }
                )
            },
            containerColor = Color(0xFFF8FAFC)
        ) { paddingValues ->

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    ScrollableSummaryRow()
                }

                item {
                    SectionHeader(
                        title = "الطلبات",
                        count = uiState.orders.size,
                        onActionClick = onFilterClick,
                        actionText = "تصفية النتائج"
                    )
                }

                items(uiState.orders, key = { it.id }) { order ->
                    OrderCard(
                        orderNumber = order.orderNumber,
                        timeElapsed = order.timeElapsed,
                        statusLabel = order.orderStatus.label,
                        statusColor = order.orderStatus.color,
                        address = order.address,
                        clientName = order.clientName,
                        amount = order.amount
                    )
                }
            }
        }
    }
}

@Preview(
    name = "Orders Screen - Arabic",
    showBackground = true,
    showSystemUi = true,
    locale = "ar"
)
@Composable
fun OrdersScreenPreview() {

    val previewViewModel = OrdersViewModel()

    OrdersScreen(
        viewModel = previewViewModel,
        onMenuClick = { },
        onNotificationClick = { },
        onFilterClick = { }
    )
}