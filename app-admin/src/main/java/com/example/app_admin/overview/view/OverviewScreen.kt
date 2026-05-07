package com.example.app_admin.overview.view

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app_admin.overview.components.DashboardAlert
import com.example.app_admin.overview.components.HourlyOrdersChart
import com.example.app_admin.overview.components.PeriodSelector
import com.example.app_admin.overview.components.RevenueBarChart
import com.example.app_admin.overview.viewModel.OverviewViewModel
import com.example.app_admin.shared.RoadAssistTopAppBar
import com.example.app_admin.shared.StatusBadge
import com.example.app_admin.shared.SummaryCard
import com.example.app_admin.theme.DarkNavy
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverviewScreen(
    viewModel: OverviewViewModel = viewModel(),
    onNotificationClick: () -> Unit = {},
    onMenuClick: () -> Unit = {}
) {
    // Senior Approach: Observe State from ViewModel
    val uiState by viewModel.uiState.collectAsState()

    val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    val greeting = when (hour) {
        in 0..11 -> "صباح الخير"
        in 12..16 -> "طاب يومك"
        else -> "مساء الخير"
    }

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val orangePrimary = Color(0xFFF99806)
    val periods = listOf("الشهر", "الأسبوع", "اليوم")

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            containerColor = Color(0xFFF8F7F5),
            topBar = {
                RoadAssistTopAppBar(
                    title = "لوحة التحكم",
                    modifier = Modifier.clip(
                        RoundedCornerShape(
                            bottomStart = 24.dp,
                            bottomEnd = 24.dp
                        )
                    ),
                    subtitle = "$greeting، مشرف النظام",
                    containerColor = DarkNavy,
                    contentColor = Color.White,
                    scrollBehavior = scrollBehavior,
                    navigationIcon = {
                        Box(
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .size(40.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(orangePrimary),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.GridView, null, tint = Color.White, modifier = Modifier.size(20.dp))
                        }
                    },
                    actions = {
                        IconButton(onClick = onNotificationClick) {
                            Icon(Icons.Default.NotificationsNone, null, tint = Color.White)
                        }
                    },
                    bottomContent = {
                        // Interaction: Update ViewModel on selection
                        PeriodSelector(
                            periods = periods,
                            selectedPeriod = uiState.selectedPeriodIndex,
                            onPeriodSelected = { viewModel.onPeriodSelected(it) },
                            activeColor = orangePrimary
                        )
                    }
                )
            }
        ) { padding ->
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = padding,
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    item { Spacer(modifier = Modifier.height(4.dp)) }

                    // --- SECTION 1: Performance Matrix (Dynamic Data) ---
                    item {
                        SectionHeaderPro("الأداء العام", "مباشر")
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            item {
                                SummaryCard(
                                    modifier = Modifier.width(160.dp),
                                    title = "التقييم العام",
                                    value = uiState.rating,
                                    icon = Icons.Default.Star,
                                    containerColor = orangePrimary,
                                    isOutlined = true
                                )
                            }
                            item {
                                SummaryCard(
                                    modifier = Modifier.width(160.dp),
                                    title = "الإيرادات",
                                    value = uiState.revenue,
                                    icon = Icons.Default.AccountBalanceWallet,
                                    containerColor = orangePrimary,
                                    isOutlined = true
                                )
                            }
                            item {
                                SummaryCard(
                                    modifier = Modifier.width(160.dp),
                                    title = "نشط الآن",
                                    value = uiState.activeTechs,
                                    icon = Icons.Default.Person,
                                    containerColor = orangePrimary,
                                    isOutlined = true
                                )
                            }
                        }
                    }

                    // --- SECTION 2: Critical Alerts ---
                    item {
                        SectionHeaderPro("تنبيهات العمليات", "إجراء مطلوب")
                        Column(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            DashboardAlert(
                                title = "طلبات متأخرة",
                                description = "هناك ٣ طلبات تجاوزت الوقت المحدد",
                                icon = Icons.Default.Warning,
                                color = orangePrimary,
                                backgroundColor = Color(0xFFFFF7ED),
                                onClick = {}
                            )
                            DashboardAlert(
                                title = "تحقق فنيين",
                                description = "٥ طلبات انضمام جديدة بانتظار المراجعة",
                                icon = Icons.Default.VerifiedUser,
                                color = DarkNavy,
                                backgroundColor = Color(0xFFEFF6FF),
                                onClick = {}
                            )
                        }
                    }

                    // --- SECTION 3: Trend Analytics (Dynamic Charts) ---
                    item {
                        SectionHeaderPro("تحليلات الاتجاه", "آخر التحديثات")
                        Column(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            ChartCard(
                                title = "تطور الطلبات",
                                label = "نشاط الطلبات المكتملة"
                            ) {
                                if (uiState.hourlyOrders.isNotEmpty()) {
                                    HourlyOrdersChart(dataPoints = uiState.hourlyOrders)
                                } else {
                                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                        Text("جاري التحميل...", fontSize = 12.sp, color = Color.Gray)
                                    }
                                }
                            }
                            ChartCard(
                                title = "تحليل الإيرادات",
                                label = "مقارنة الدخل اليومي",
                                hasLegend = true
                            ) {
                                if (uiState.todayRevenue.isNotEmpty() || uiState.yesterdayRevenue.isNotEmpty()) {
                                    RevenueBarChart(
                                        todayData = uiState.todayRevenue,
                                        yesterdayData = uiState.yesterdayRevenue
                                    )
                                } else {
                                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                        Text("جاري التحميل...", fontSize = 12.sp, color = Color.Gray)
                                    }
                                }
                            }
                        }
                    }

                    item { Spacer(modifier = Modifier.height(32.dp)) }
                }

                // Professional Overlay: Show loading indicator over the list
                if (uiState.isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White.copy(alpha = 0.5f)),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = orangePrimary)
                    }
                }
            }
        }
    }
}

@Composable
fun SectionHeaderPro(title: String, badge: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold,
            color = DarkNavy
        )
        StatusBadge(label = badge, color = Color(0xFF64748B))
    }
}

@Composable
fun ChartCard(
    title: String,
    label: String,
    hasLegend: Boolean = false,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFF1F5F9)),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (hasLegend) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        LegendItem("أمس", DarkNavy)
                        LegendItem("اليوم", Color(0xFFF99806))
                    }
                } else {
                    Text(text = label, fontSize = 12.sp, color = Color(0xFF94A3B8))
                }

                Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = DarkNavy)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .padding(top = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(Color(0xFFF8FAFC), Color.White)
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()) {
                    content()
                }
            }
        }
    }
}

@Composable
fun LegendItem(text: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = text, fontSize = 10.sp, color = Color(0xFF94A3B8))
        Spacer(Modifier.width(4.dp))
        Box(
            Modifier
                .size(8.dp)
                .background(color, RoundedCornerShape(4.dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OverviewScreenPreview() {
    MaterialTheme {
        OverviewScreen()
    }
}