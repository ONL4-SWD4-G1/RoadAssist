package com.example.app_admin.finance.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_admin.finance.view.component.SummaryCard
import com.example.app_admin.finance.view.component.TransactionItem
import com.example.app_admin.shared.RoadAssistTabRow
import com.example.app_admin.shared.RoadAssistTopAppBar
import com.example.app_admin.theme.DarkNavy
import com.example.app_admin.theme.DeepBlue
import com.example.app_admin.theme.LightText
import com.example.app_admin.theme.PrimaryOrange
import com.example.app_admin.theme.SoftGray
import com.example.app_admin.transactions


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinanceScreen() {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("المعاملات", "طلبات الصرف", "التقارير")

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = Color.White,
        topBar = {
            RoadAssistTopAppBar(
                title = "المحفظة والمالية",
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = SoftGray,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(45.dp)
                    ) {
                        IconButton(onClick = { /* Handle Notifications */ }) {
                            Icon(
                                imageVector = Icons.Default.NotificationsNone,
                                contentDescription = null,
                                tint = DarkNavy
                            )
                        }
                    }
                },
                actions = {
                    IconButton(onClick = { /* Handle Menu */ }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = null,
                            tint = DarkNavy
                        )
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                Text(
                    "ملخص مالي",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkNavy,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    textAlign = TextAlign.Start
                )
            }

            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        SummaryCard(
                            modifier = Modifier.weight(1f),
                            title = "إيرادات اليوم",
                            value = "1,250 ر.س",
                            icon = Icons.Default.Payments,
                            containerColor = DeepBlue
                        )
                        SummaryCard(
                            modifier = Modifier.weight(1f),
                            title = "الإيراد الشهري",
                            value = "34,800 ر.س",
                            icon = Icons.Default.CalendarMonth,
                            containerColor = PrimaryOrange
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        SummaryCard(
                            modifier = Modifier.weight(1f), "إجمالي العمولات",
                            value = "5,420 ر.س",
                            icon = Icons.Default.AccountBalanceWallet,
                            containerColor = DarkNavy
                        )
                        SummaryCard(
                            modifier = Modifier.weight(1f),
                            title = "مدفوعات معلقة",
                            value = "2,100 ر.س",
                            icon = Icons.Default.HourglassEmpty,
                            containerColor = DeepBlue,
                            isOutlined = true
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))

                RoadAssistTabRow(
                    tabs = tabs,
                    selectedTabIndex = selectedTab,
                    onTabSelected = { selectedTab = it },
                    modifier = Modifier.fillMaxWidth(),
                    isScrollable = false,
                    containerColor = Color.Transparent,
                    selectedContentColor = DeepBlue,
                    unselectedContentColor = LightText
                )
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("فبراير 2024", color = LightText, fontSize = 12.sp)
                    Text(
                        "العمليات الأخيرة",
                        fontWeight = FontWeight.Bold,
                        color = LightText, fontSize = 12.sp
                    )
                }
            }
            items(transactions) { transaction ->
                TransactionItem(transaction)
            }
        }
    }
}

@Preview(showBackground = true, name = "Finance Screen Preview", locale = "ar")
@Composable
fun FinanceScreenPreview() {
    MaterialTheme {
        FinanceScreen()
    }
}