package com.example.app_mechanic

import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Work
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//Colors
val BackgroundDark   = Color(0xFF0D1117)
val SurfaceDark      = Color(0xFF161B22)
val CardDark         = Color(0xFF1C2333)
val CardAlt          = Color(0xFF1A2236)
val AccentBlue       = Color(0xFF2563EB)
val AccentBlueDim    = Color(0xFF1D3A6E)
val TextPrimary      = Color(0xFFE6EDF3)
val TextSecondary    = Color(0xFF8B949E)
val TextMuted        = Color(0xFF656D76)
val GreenAccent      = Color(0xFF3FB950)
val GoldStar         = Color(0xFFF0C048)
val NavSelected      = Color(0xFF2F81F7)

//Bar chart data
data class BarData(val label: String, val value: Float, val isHighlighted: Boolean = false)

val weekBars = listOf(
    BarData("M", 0.45f),
    BarData("T", 0.60f),
    BarData("W", 0.55f),
    BarData("T", 0.75f),
    BarData("F", 1.00f, true),
    BarData("S", 0.30f),
    BarData("S", 0.20f),
)

//Nav items
data class NavItem(val label: String, val icon: ImageVector, val selected: Boolean = false)

val navItems = listOf(
    NavItem("JOBS",     Icons.Outlined.Work),
    NavItem("EARNINGS", Icons.Outlined.AccountBalanceWallet, selected = true),
    NavItem("PROFILE",  Icons.Outlined.Person),
    NavItem("SETTINGS", Icons.Outlined.Settings),
)

//Main Screen
@Composable
fun EarningsDashboardScreen() {
    Scaffold(
        containerColor = BackgroundDark,
        bottomBar      = { BottomNavBar() }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            TopBar()
            Spacer(Modifier.height(20.dp))
            QuickStatsRow()
            Spacer(Modifier.height(28.dp))
            SectionLabel("Earnings Overview")
            Spacer(Modifier.height(12.dp))
            EarningsCard(
                period       = "TODAY",
                amount       = "\$145.00",
                subtitle     = "4 jobs completed",
                icon         = Icons.Outlined.CreditCard,
                isHighlighted = true
            )
            Spacer(Modifier.height(10.dp))
            EarningsCard(
                period   = "THIS WEEK",
                amount   = "\$850.24",
                subtitle = "22 jobs completed",
                icon     = Icons.Outlined.BarChart
            )
            Spacer(Modifier.height(10.dp))
            EarningsCard(
                period   = "THIS MONTH",
                amount   = "\$3,420.00",
                subtitle = "88 jobs completed",
                icon     = Icons.Outlined.CalendarMonth
            )
            Spacer(Modifier.height(28.dp))
            PerformanceSection()
            Spacer(Modifier.height(24.dp))
        }
    }
}

//Top Bar
@Composable
fun TopBar() {
    Row(
        modifier            = Modifier
            .fillMaxWidth()
            .background(SurfaceDark)
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment   = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            Icons.Default.Menu,
            contentDescription = "Menu",
            tint               = TextPrimary,
            modifier           = Modifier.size(24.dp)
        )
        Text(
            text       = "Earnings Dashboard",
            color      = TextPrimary,
            fontSize   = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
        Box(
            modifier        = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(CardDark),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Outlined.NotificationsNone,
                contentDescription = "Notifications",
                tint               = TextPrimary,
                modifier           = Modifier.size(20.dp)
            )
        }
    }
}

//Quick Stats Row
@Composable
fun QuickStatsRow() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text     = "QUICK STATS",
            color    = TextMuted,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.5.sp
        )
        Spacer(Modifier.height(10.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            StatCard(
                modifier = Modifier.weight(1f),
                label    = "Completed Jobs",
                value    = "124",
                badge    = "+12%",
                badgeColor = GreenAccent
            )
            StatCard(
                modifier   = Modifier.weight(1f),
                label      = "Avg. Rating",
                value      = "4.9",
                showStar   = true
            )
        }
    }
}

@Composable
fun StatCard(
    modifier   : Modifier = Modifier,
    label      : String,
    value      : String,
    badge      : String?  = null,
    badgeColor : Color    = GreenAccent,
    showStar   : Boolean  = false,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(CardDark)
            .padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
        Column {
            Text(label, color = TextSecondary, fontSize = 12.sp)
            Spacer(Modifier.height(6.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(value, color = TextPrimary, fontSize = 26.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.width(6.dp))
                if (badge != null) {
                    Text(
                        text     = "↗$badge",
                        color    = badgeColor,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                if (showStar) {
                    Icon(
                        Icons.Default.Star,
                        contentDescription = null,
                        tint     = GoldStar,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

//Section Label
@Composable
fun SectionLabel(text: String) {
    Text(
        text       = text,
        color      = TextPrimary,
        fontSize   = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier   = Modifier.padding(horizontal = 16.dp)
    )
}

//Earnings Card
@Composable
fun EarningsCard(
    period        : String,
    amount        : String,
    subtitle      : String,
    icon          : ImageVector,
    isHighlighted : Boolean = false,
) {
    val bgGradient = if (isHighlighted) Brush.horizontalGradient(
        listOf(Color(0xFF1A2A5E), Color(0xFF1C2333))
    ) else null

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .then(
                if (bgGradient != null)
                    Modifier.background(bgGradient)
                else
                    Modifier.background(CardDark)
            )
            .padding(horizontal = 20.dp, vertical = 18.dp)
    ) {
        Row(
            modifier              = Modifier.fillMaxWidth(),
            verticalAlignment     = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text          = period,
                    color         = if (isHighlighted) NavSelected else TextSecondary,
                    fontSize      = 11.sp,
                    fontWeight    = FontWeight.Bold,
                    letterSpacing = 1.2.sp
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text       = amount,
                    color      = TextPrimary,
                    fontSize   = 30.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(Modifier.height(2.dp))
                Text(subtitle, color = TextSecondary, fontSize = 13.sp)
            }
            Box(
                modifier         = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(if (isHighlighted) AccentBlue else AccentBlueDim),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(26.dp))
            }
        }
    }
}

//Performance Section
@Composable
fun PerformanceSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Row(
            modifier              = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment     = Alignment.CenterVertically
        ) {
            Text(
                "Performance",
                color      = TextPrimary,
                fontSize   = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                "View Reports",
                color    = NavSelected,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Spacer(Modifier.height(14.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(CardDark)
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Column {
                Text(
                    "Daily earnings this week",
                    color    = TextMuted,
                    fontSize = 13.sp
                )
                Spacer(Modifier.height(16.dp))
                WeekBarChart(bars = weekBars, chartHeight = 120.dp)
            }
        }
    }
}

//Bar Chart
@Composable
fun WeekBarChart(bars: List<BarData>, chartHeight: Dp) {
    val animProgress by animateFloatAsState(
        targetValue  = 1f,
        animationSpec = tween(durationMillis = 900, easing = EaseOutCubic),
        label        = "barAnim"
    )

    Column {
        Row(
            modifier              = Modifier
                .fillMaxWidth()
                .height(chartHeight),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment     = Alignment.Bottom
        ) {
            bars.forEach { bar ->
                val barHeight = chartHeight * bar.value * animProgress
                Box(
                    modifier = Modifier
                        .width(28.dp)
                        .height(barHeight)
                        .clip(RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp))
                        .background(
                            if (bar.isHighlighted)
                                Brush.verticalGradient(listOf(NavSelected, AccentBlueDim))
                            else
                                Brush.verticalGradient(listOf(Color(0xFF2A3A5C), Color(0xFF1C2745)))
                        )
                )
            }
        }
        Spacer(Modifier.height(8.dp))
        Row(
            modifier              = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            bars.forEach { bar ->
                Text(
                    text      = bar.label,
                    color     = if (bar.isHighlighted) NavSelected else TextMuted,
                    fontSize  = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier  = Modifier.width(28.dp),
                    fontWeight = if (bar.isHighlighted) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}

//Bottom Nav Bar
@Composable
fun BottomNavBar() {
    NavigationBar(
        containerColor = SurfaceDark,
        tonalElevation = 0.dp
    ) {
        navItems.forEach { item ->
            NavigationBarItem(
                selected = item.selected,
                onClick  = {},
                icon     = {
                    Icon(
                        item.icon,
                        contentDescription = item.label,
                        modifier           = Modifier.size(22.dp)
                    )
                },
                label    = {
                    Text(
                        item.label,
                        fontSize   = 9.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 0.8.sp
                    )
                },
                colors   = NavigationBarItemDefaults.colors(
                    selectedIconColor   = NavSelected,
                    selectedTextColor   = NavSelected,
                    unselectedIconColor = TextMuted,
                    unselectedTextColor = TextMuted,
                    indicatorColor      = Color.Transparent
                )
            )
        }
    }
}

//Preview
@Preview(showBackground = true, backgroundColor = 0xFF0D1117, showSystemUi = true)
@Composable
fun EarningsDashboardPreview() {
    MaterialTheme(colorScheme = darkColorScheme()) {
        EarningsDashboardScreen()
    }
}