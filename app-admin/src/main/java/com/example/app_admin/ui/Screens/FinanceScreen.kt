package com.example.app_admin.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_admin.R
import com.example.app_admin.ui.model.TransactionData
import com.example.app_admin.ui.model.transactions
import com.example.app_admin.ui.theme.DangerBg
import com.example.app_admin.ui.theme.DangerRed
import com.example.app_admin.ui.theme.DarkNavy
import com.example.app_admin.ui.theme.DeepBlue
import com.example.app_admin.ui.theme.LightText
import com.example.app_admin.ui.theme.PrimaryOrange
import com.example.app_admin.ui.theme.SoftGray
import com.example.app_admin.ui.theme.SuccessBg
import com.example.app_admin.ui.theme.SuccessGreen

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
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                    textAlign = TextAlign.End
                )
            }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        SummaryCard(Modifier.weight(1f), "إيرادات اليوم"
                            , "1,250 ر.س", Icons.Default.Payments,
                            DeepBlue)
                        SummaryCard(Modifier.weight(1f),
                            "الإيراد الشهري",
                            "34,800 ر.س",
                            Icons.Default.CalendarMonth,
                            PrimaryOrange)
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        SummaryCard(Modifier.weight(1f), "إجمالي العمولات",
                            "5,420 ر.س",
                            Icons.Default.AccountBalanceWallet, DarkNavy)
                        SummaryCard(Modifier.weight(1f),
                            "مدفوعات معلقة",
                            "2,100 ر.س",
                            Icons.Default.HourglassEmpty,
                            DeepBlue, isOutlined = true)
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
                    modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("فبراير 2024", color = LightText, fontSize = 12.sp)
                    Text("العمليات الأخيرة",
                        fontWeight = FontWeight.Bold,
                        color = LightText, fontSize = 12.sp)
                }
            }
            items(transactions) { transaction ->
                TransactionItem(transaction)
            }
        }
    }
}

@Composable
fun FinanceHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = SoftGray,
            modifier = Modifier.size(45.dp)
        ) {
            IconButton(onClick = {})
            { Icon(Icons.Default.NotificationsNone,
                null) }
        }

        Text("المحفظة والمالية", fontWeight = FontWeight.ExtraBold,
            fontSize = 18.sp, color = DarkNavy)

        IconButton(onClick = {}) { Icon(Icons.Default.Menu,
            null) }
    }
}

@Composable
fun SummaryCard(
    modifier: Modifier,
    title: String,
    value: String,
    icon: ImageVector,
    color: Color,
    isOutlined: Boolean = false
) {
    Card(
        modifier = modifier.height(110.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = if (isOutlined) Color.White else color),
        border = if (isOutlined) BorderStroke(1.dp, color.copy(alpha = 0.3f)) else null
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            if (!isOutlined) {
                Image(
                    painter = painterResource(id = R.drawable.overlay),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = (10).dp, y = (-10).dp)
                        .size(90.dp),
                    alpha = 0.15f
                )
            }

            Box(modifier = Modifier.fillMaxSize().padding(12.dp)) {
                Icon(
                    icon, null,
                    modifier = Modifier.align(Alignment.TopEnd).size(24.dp),
                    tint = if (isOutlined) color else Color.White.copy(alpha = 0.7f)
                )
                Column(modifier = Modifier.align(Alignment.BottomEnd), horizontalAlignment = Alignment.End) {
                    Text(title, color =
                        if (isOutlined) LightText
                        else Color.White.copy(alpha = 0.9f), fontSize = 11.sp)
                    Text(value, color =
                        if (isOutlined) color
                        else Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
            }
        }
    }
}

@Composable
fun TransactionItem(transaction: TransactionData) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xffF6F6F8)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Column(horizontalAlignment = Alignment.Start) {
                Text(
                    "${if(transaction.isPositive) "+" else ""}${transaction.amount} ر.س",
                    color =
                        if(transaction.isPositive) SuccessGreen
                    else DangerRed,
                    fontWeight = FontWeight.Bold
                )
                Text(transaction.status, color = LightText, fontSize = 11.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(horizontalAlignment = Alignment.End) {
                Text(transaction.title, fontWeight = FontWeight.Bold, color = DarkNavy, fontSize = 14.sp)
                Text(transaction.date, color = LightText, fontSize = 11.sp)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .background(if (transaction.isPositive) SuccessBg else if (transaction.title.contains("تحويل")) Color(0xFFF1F5F9) else DangerBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector =
                        if(transaction.title.contains("تحويل"))
                        Icons.Default.AccountBalance
                    else if(transaction.isPositive) Icons.Default.Add
                        else Icons.Default.RemoveCircleOutline,
                    contentDescription = null,
                    tint =
                        if(transaction.isPositive) SuccessGreen
                        else if (transaction.title.contains("تحويل")) DeepBlue
                        else DangerRed
                )
            }
        }
    }
}

