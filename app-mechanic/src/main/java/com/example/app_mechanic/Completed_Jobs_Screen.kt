package com.example.app_mechanic

//import androidx.compose.material.icons.automirrored.outlined.ArrowBackIos
//import androidx.compose.material.icons.filled.StarHalf
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBackIos
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.darkColorScheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


//Colors
private val BgDark = Color(0xFF0D1117)
private val SurfaceDark2 = Color(0xFF161B22)
private val CardDark2 = Color(0xFF1C2333)
private val AccentBlue2 = Color(0xFF2F81F7)
private val TextPrimary2 = Color(0xFFE6EDF3)
private val TextSecondary2 = Color(0xFF8B949E)
private val TextMuted2 = Color(0xFF656D76)
private val GoldStar2 = Color(0xFFF0C048)
private val Divider = Color(0xFF21262D)
private val NavSelected2 = Color(0xFF2F81F7)

//Data Models
data class JobRecord(
    val name: String,
    val service: String,
    val date: String,
    val amount: String,
    val rating: Float,          // e.g. 4.5
    val avatarColor: Color           // placeholder avatar bg
)

//Sample Data
private val recentJobs = listOf(
    JobRecord("John Anderson", "Tire Change", "Oct 24, 2023", "\$120.00", 5.0f, Color(0xFF3D5A80)),
    JobRecord("Sarah Jenkins", "Battery Jump", "Oct 22, 2023", "\$85.50", 4.0f, Color(0xFF5C4B6B)),
    JobRecord(
        "Michael Chen",
        "Towing Service",
        "Oct 19, 2023",
        "\$245.00",
        5.0f,
        Color(0xFF3B5249)
    ),
)
private val earlierJobs = listOf(
    JobRecord("Emily Davis", "Lockout Service", "Oct 15, 2023", "\$60.00", 5.0f, Color(0xFF5A3E36)),
    JobRecord(
        "Robert Wilson",
        "Fuel Delivery",
        "Oct 10, 2023",
        "\$150.00",
        3.5f,
        Color(0xFF4A4035)
    ),
)

//Tab Model
private val tabs = listOf("Completed", "Canceled", "In Progress")

//Nav Item Model
private data class NavItem2(val label: String, val icon: ImageVector, val selected: Boolean = false)

private val navItems2 = listOf(
    NavItem2("Home", Icons.Outlined.Home),
    NavItem2("History", Icons.Outlined.History, selected = true),
    NavItem2("Earnings", Icons.Outlined.AccountBalanceWallet),
    NavItem2("Profile", Icons.Outlined.Person),
)

//Main Screen
@Composable
fun JobHistoryScreen() {
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        containerColor = BgDark,
        topBar = { JobHistoryTopBar() },
        bottomBar = { JobHistoryBottomNav() }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            //Tabs
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = BgDark,
                contentColor = AccentBlue,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                        color = AccentBlue,
                        height = 2.dp
                    )
                },
                divider = {
                    HorizontalDivider(color = Divider, thickness = 1.dp)
                }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = {
                            Text(
                                text = title,
                                fontSize = 14.sp,
                                fontWeight = if (selectedTab == index) FontWeight.SemiBold
                                else FontWeight.Normal
                            )
                        },
                        selectedContentColor = AccentBlue,
                        unselectedContentColor = TextSecondary
                    )
                }
            }

            //Job List
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item { SectionHeader("RECENT JOBS") }
                items(recentJobs) { job -> JobCard(job) }
                item { Spacer(Modifier.height(8.dp)) }
                item { SectionHeader("EARLIER THIS MONTH") }
                items(earlierJobs) { job -> JobCard(job) }
            }
        }
    }
}

// ─── Top Bar ─────────────────────────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobHistoryTopBar() {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    Icons.AutoMirrored.Outlined.ArrowBackIos,
                    contentDescription = "Back",
                    tint = TextPrimary,
                    modifier = Modifier.size(20.dp)
                )
            }
        },
        title = {
            Text(
                text = "Job History",
                color = TextPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    Icons.Outlined.Search,
                    contentDescription = "Search",
                    tint = TextPrimary,
                    modifier = Modifier.size(22.dp)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = BgDark)
    )
}

// ─── Section Header ──────────────────────────────────────────────────────────
@Composable
fun SectionHeader(text: String) {
    Text(
        text = text,
        color = TextMuted,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.4.sp,
        modifier = Modifier.padding(bottom = 6.dp, top = 4.dp)
    )
}

// ─── Job Card ────────────────────────────────────────────────────────────────
@Composable
fun JobCard(job: JobRecord) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(CardDark)
            .padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar placeholder
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(job.avatarColor),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = job.name.first().toString(),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.width(14.dp))

            // Name + service + stars
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = job.name,
                    color = TextPrimary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = "${job.service} • ${job.date}",
                    color = TextSecondary,
                    fontSize = 12.sp
                )
                Spacer(Modifier.height(6.dp))
                StarRating(rating = job.rating)
            }

            // Amount
            Text(
                text = job.amount,
                color = TextPrimary,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// ─── Star Rating ────────────────────────────────────────────────────────────
@Composable
fun StarRating(rating: Float) {
    Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
        for (i in 1..5) {
            val starIcon = when {
                i <= rating.toInt() -> Icons.Filled.Star
                i - rating < 1f && i > rating -> Icons.Filled.StarHalf
                else -> Icons.Outlined.StarOutline
            }
            Icon(
                imageVector = starIcon,
                contentDescription = null,
                tint = GoldStar,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

// ─── Bottom Nav ──────────────────────────────────────────────────────────────
@Composable
fun JobHistoryBottomNav() {
    NavigationBar(
        containerColor = SurfaceDark,
        tonalElevation = 0.dp
    ) {
        navItems2.forEach { item ->
            NavigationBarItem(
                selected = item.selected,
                onClick = {},
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = item.label,
                        modifier = Modifier.size(22.dp)
                    )
                },
                label = {
                    Text(
                        item.label,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 0.5.sp
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = NavSelected,
                    selectedTextColor = NavSelected,
                    unselectedIconColor = TextMuted,
                    unselectedTextColor = TextMuted,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

// ─── Preview ─────────────────────────────────────────────────────────────────
@Preview(showBackground = true, backgroundColor = 0xFF0D1117, showSystemUi = true)
@Composable
fun JobHistoryPreview() {
    MaterialTheme(colorScheme = darkColorScheme()) {
        JobHistoryScreen()
    }
}