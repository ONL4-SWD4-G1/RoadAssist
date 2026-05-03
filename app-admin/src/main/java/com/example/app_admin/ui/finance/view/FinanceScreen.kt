package com.example.app_admin.ui.finance.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_admin.ui.technicians.model.transactions
import com.example.app_admin.ui.finance.view.component.SummaryCard
import com.example.app_admin.ui.finance.view.component.TransactionItem
import com.example.app_admin.ui.theme.*

@Composable
fun FinanceScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("المعاملات", "طلبات الصرف", "التقارير")

    Scaffold(
        containerColor = Color.White,
        topBar = { FinanceHeader() }
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
                TabRow(
                    selectedTabIndex = selectedTab,
                    containerColor = Color.Transparent,
                    contentColor = DeepBlue,
                    divider = {},
                    indicator = { tabPositions ->
                        TabRowDefaults.SecondaryIndicator(
                            Modifier.
                            tabIndicatorOffset(tabPositions[selectedTab]),
                            color = DeepBlue
                        )
                    }
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = { Text(title, fontSize = 14.sp, fontWeight = FontWeight.Bold) }
                        )
                    }
                }
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

//@Composable
//fun SummaryCard(
//    modifier: Modifier,
//    title: String,
//    value: String,
//    icon: ImageVector,
//    color: Color,
//    isOutlined: Boolean = false
//) {
//    Card(
//        modifier = modifier.height(110.dp),
//        shape = RoundedCornerShape(16.dp),
//        colors = CardDefaults.cardColors(containerColor = if (isOutlined) Color.White else color),
//        border = if (isOutlined) BorderStroke(1.dp, color.copy(alpha = 0.3f)) else null
//    ) {
//        Box(modifier = Modifier.fillMaxSize()) {
//
//            if (!isOutlined) {
//                Image(
//                    painter = painterResource(id = R.drawable.overlay),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .align(Alignment.TopEnd)
//                        .offset(x = (10).dp, y = (-10).dp)
//                        .size(90.dp),
//                    alpha = 0.15f
//                )
//            }
//
//            Box(modifier = Modifier.fillMaxSize().padding(12.dp)) {
//                Icon(
//                    icon, null,
//                    modifier = Modifier.align(Alignment.TopEnd).size(24.dp),
//                    tint = if (isOutlined) color else Color.White.copy(alpha = 0.7f)
//                )
//                Column(modifier = Modifier.align(Alignment.BottomEnd), horizontalAlignment = Alignment.End) {
//                    Text(title, color =
//                        if (isOutlined) LightText
//                        else Color.White.copy(alpha = 0.9f), fontSize = 11.sp)
//                    Text(value, color =
//                        if (isOutlined) color
//                        else Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
//                }
//            }
//        }
//    }
//}

//@Composable
//fun TransactionItem(transaction: TransactionData) {
//    Card(
//        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
//        colors = CardDefaults.cardColors(containerColor = Color(0xffF6F6F8)),
//        shape = RoundedCornerShape(16.dp)
//    ) {
//        Row(
//            modifier = Modifier.padding(12.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.End
//        ) {
//            Column(horizontalAlignment = Alignment.Start) {
//                Text(
//                    "${if(transaction.isPositive) "+" else ""}${transaction.amount} ر.س",
//                    color =
//                        if(transaction.isPositive) SuccessGreen
//                    else DangerRed,
//                    fontWeight = FontWeight.Bold
//                )
//                Text(transaction.status, color = LightText, fontSize = 11.sp)
//            }
//            Spacer(modifier = Modifier.weight(1f))
//            Column(horizontalAlignment = Alignment.End) {
//                Text(transaction.title, fontWeight = FontWeight.Bold, color = DarkNavy, fontSize = 14.sp)
//                Text(transaction.date, color = LightText, fontSize = 11.sp)
//            }
//            Spacer(modifier = Modifier.width(12.dp))
//            Box(
//                modifier = Modifier
//                    .size(45.dp)
//                    .clip(CircleShape)
//                    .background(if (transaction.isPositive) SuccessBg else if (transaction.title.contains("تحويل")) Color(0xFFF1F5F9) else DangerBg),
//                contentAlignment = Alignment.Center
//            ) {
//                Icon(
//                    imageVector =
//                        if(transaction.title.contains("تحويل"))
//                        Icons.Default.AccountBalance
//                    else if(transaction.isPositive) Icons.Default.Add
//                        else Icons.Default.RemoveCircleOutline,
//                    contentDescription = null,
//                    tint =
//                        if(transaction.isPositive) SuccessGreen
//                        else if (transaction.title.contains("تحويل")) DeepBlue
//                        else DangerRed
//                )
//            }
//        }
//    }
//}

