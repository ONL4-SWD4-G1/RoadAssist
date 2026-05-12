package com.example.app_admin.orders.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app_admin.shared.SummaryCard

@Composable
fun ScrollableSummaryRow() {
    LazyRow(
        modifier = Modifier.Companion.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        item {
            SummaryCard(
                modifier = Modifier.Companion.width(160.dp),
                title = "طلبات نشطة",
                value = "٤٢",
                icon = Icons.Default.AccountBalanceWallet,
                containerColor = Color(0xFFF5A623),
                isOutlined = true
            )
        }
        item {
            SummaryCard(
                modifier = Modifier.Companion.width(160.dp),
                title = "متوسط السعر",
                value = "٤.٨",
                icon = Icons.Default.AttachMoney,
                containerColor = Color(0xFF10B981),
                isOutlined = true
            )
        }
        item {
            SummaryCard(
                modifier = Modifier.Companion.width(160.dp),
                title = "وقت الانجاز",
                value = "٥",
                icon = Icons.Default.AccessTime,
                containerColor = Color(0xFFF87171),
                isOutlined = true,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScrollableSummaryRowPreview() {
    ScrollableSummaryRow()
}