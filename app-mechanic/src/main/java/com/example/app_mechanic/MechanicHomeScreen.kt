package com.example.app_mechanic

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Work
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



// ─── Main Screen ─────────────────────────────────────────────────────────────
@Composable
fun MechanicHomeScreen(modifier: Modifier = Modifier) {
    var isOnline by remember { mutableStateOf(true) }
    var selectedTab by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            // ── Top Bar ──────────────────────────────────────────────────────
            TopBar(isOnline = isOnline, onToggle = { isOnline = !isOnline })

            // ── Map Area ─────────────────────────────────────────────────────
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                MapPlaceholder()

                // Search Bar overlay
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .align(Alignment.TopCenter)
                )

                // Zoom Minus Button
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 68.dp, end = 16.dp)
                        .size(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(CardBg),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Remove,
                        contentDescription = "Zoom out",
                        tint = TextPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                }

                // Location Pin (mechanic's position)
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(x = (-20).dp, y = 10.dp)
                ) {
                    LocationPin()
                }

                // ── Service Request Card ──────────────────────────────────────
                ServiceRequestCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            // ── Bottom Navigation ─────────────────────────────────────────────
            BottomNavBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        }
    }
}

// ─── Top Bar ─────────────────────────────────────────────────────────────────
@Composable
fun TopBar(isOnline: Boolean, onToggle: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkBg)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar
        Box(
            modifier = Modifier
                .size(46.dp)
                .clip(CircleShape)
                .background(Color(0xFFB45309)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Mike Thompson",
                color = TextPrimary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "EXPERT MECHANIC",
                color = AccentBlue,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 0.5.sp
            )
        }

        // Online Toggle
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(CardBg)
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Text(
                text = if (isOnline) "Online" else "Offline",
                color = TextSecondary,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = isOnline,
                onCheckedChange = { onToggle() },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = AccentBlue,
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = TextSecondary.copy(alpha = 0.4f)
                ),
                modifier = Modifier.height(24.dp)
            )
        }
    }
}

// ─── Map Placeholder ─────────────────────────────────────────────────────────
@Composable
private fun MapPlaceholder() {
    // Simulates the Google Maps dark-mode look with a colored background
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1E2D3A))
    ) {
        // Water bodies (bay area simulation)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .align(Alignment.TopCenter)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF1B3A5C), Color(0xFF0F2740))
                    )
                )
        )

        // Land masses (simplified)
        Box(
            modifier = Modifier
                .fillMaxWidth(0.65f)
                .height(300.dp)
                .align(Alignment.Center)
                .clip(RoundedCornerShape(topStart = 40.dp, bottomEnd = 40.dp))
                .background(Color(0xFF263D2E))
        )

        // Street grid lines (decorative)
        repeat(6) { i ->
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (80 + i * 55).dp),
                color = Color(0xFF2A3A45).copy(alpha = 0.6f),
                thickness = 1.dp
            )
        }
        repeat(5) { i ->
            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight()
                    .offset(x = (50 + i * 80).dp),
                color = Color(0xFF2A3A45).copy(alpha = 0.4f),
                thickness = 1.dp
            )
        }

        // Map Labels (decorative)
        Text(
            text = "San Francisco",
            color = TextSecondary.copy(alpha = 0.7f),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .align(Alignment.Center)
                .offset(x = 50.dp, y = 30.dp)
        )
        Text(
            text = "Golden Gate Park",
            color = TextSecondary.copy(alpha = 0.5f),
            fontSize = 10.sp,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 60.dp, bottom = 160.dp)
        )
        Text(
            text = "Alcatraz Island",
            color = TextSecondary.copy(alpha = 0.5f),
            fontSize = 10.sp,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 100.dp, end = 20.dp)
        )
        Text(
            text = "Golden Gate Bridge",
            color = TextSecondary.copy(alpha = 0.5f),
            fontSize = 10.sp,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 140.dp)
        )
        Text(
            text = "Sausalito",
            color = TextSecondary.copy(alpha = 0.5f),
            fontSize = 10.sp,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 80.dp, start = 80.dp)
        )

        // Google Branding
        Text(
            text = "Google",
            color = TextSecondary.copy(alpha = 0.4f),
            fontSize = 10.sp,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 8.dp, bottom = 8.dp)
        )
    }
}

// ─── Location Pin ─────────────────────────────────────────────────────────────
@Composable
fun LocationPin() {
    Box(contentAlignment = Alignment.Center) {
        // Pulse ring
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(AccentBlue.copy(alpha = 0.2f))
        )
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(AccentBlue.copy(alpha = 0.35f))
        )
        // Core pin
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(AccentBlue)
                .border(2.dp, Color.White, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Navigation,
                contentDescription = "My Location",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

// ─── Search Bar ──────────────────────────────────────────────────────────────
@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(CardBg.copy(alpha = 0.95f))
            .padding(horizontal = 14.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            tint = TextSecondary,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "Search service area...",
            color = TextSecondary,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.Tune,
            contentDescription = "Filter",
            tint = AccentBlue,
            modifier = Modifier.size(20.dp)
        )
    }
}

// ─── Service Request Card ────────────────────────────────────────────────────
@Composable
fun ServiceRequestCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardBg),
        elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // ── Header Row ───────────────────────────────────────────────────
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Car icon in orange box
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            Brush.linearGradient(
                                colors = listOf(Color(0xFF7C2D12), Color(0xFFB45309))
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.DirectionsCar,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "New Service Request",
                        color = TextPrimary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "John Doe",
                            color = TextSecondary,
                            fontSize = 13.sp
                        )
                        Text(
                            text = " • ",
                            color = TextSecondary,
                            fontSize = 13.sp
                        )
                        Text(
                            text = "Toyota Camry",
                            color = TextBlue,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                // ETA
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "10 mins",
                        color = TextBlue,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "ETA",
                        color = TextSecondary,
                        fontSize = 11.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))
            Divider(color = Color(0xFF2D3748), thickness = 1.dp)
            Spacer(modifier = Modifier.height(14.dp))

            // ── Info Row ─────────────────────────────────────────────────────
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Issue
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = TextSecondary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Engine Overheat",
                        color = TextPrimary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                // Distance
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = TextSecondary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "2.5 km away",
                        color = TextPrimary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // ── View Details / Skip Row ───────────────────────────────────────
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // View Details button
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = AccentBlue
                    ),
                    border = androidx.compose.foundation.BorderStroke(1.dp, AccentBlue.copy(alpha = 0.5f))
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "View Details",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                // Skip button
                TextButton(
                    onClick = {},
                    modifier = Modifier
                        .wrapContentWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(CardBgLight),
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = TextSecondary
                    )
                ) {
                    Text(
                        text = "Skip",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Icon(
                        imageVector = Icons.Default.SkipNext,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // ── Reject / Accept Row ───────────────────────────────────────────
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Reject
                Button(
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3D1515),
                        contentColor = AccentRed
                    )
                ) {
                    Text(
                        text = "Reject",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }

                // Accept Job
                Button(
                    onClick = {},
                    modifier = Modifier.weight(2f),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AccentGreen,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Accept Job",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }
    }
}

// ─── Bottom Navigation Bar ───────────────────────────────────────────────────
@Composable
fun BottomNavBar(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    val tabs = listOf(
        HomeNavItem(
            "Home",
            Icons.Default.Home,
            Icons.Outlined.Home
        ),
        HomeNavItem(
            "Jobs",
            Icons.Default.Work,
            Icons.Outlined.Work
        ),
        HomeNavItem(
            "Earnings",
            Icons.Default.AccountBalanceWallet,
            Icons.Outlined.AccountBalanceWallet
        ),
        HomeNavItem(
            "Profile",
            Icons.Default.Person,
            Icons.Outlined.Person
        )
    )

    NavigationBar(
        containerColor = CardBg,
        contentColor = NavUnselected,
        tonalElevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
    ) {
        tabs.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedTab == index,
                onClick = { onTabSelected(index) },
                icon = {
                    Icon(
                        imageVector = if (selectedTab == index) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.label,
                        modifier = Modifier.size(22.dp)
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = 11.sp,
                        fontWeight = if (selectedTab == index) FontWeight.SemiBold else FontWeight.Normal
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = NavSelected,
                    selectedTextColor = NavSelected,
                    unselectedIconColor = NavUnselected,
                    unselectedTextColor = NavUnselected,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

// ─── Data Classes ─────────────────────────────────────────────────────────────
private data class HomeNavItem(
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

// ─── Helper Composables ───────────────────────────────────────────────────────
@Composable
fun VerticalDivider(
    modifier: Modifier = Modifier,
    color: Color = Color.Gray,
    thickness: androidx.compose.ui.unit.Dp = 1.dp
) {
    Box(
        modifier = modifier
            .width(thickness)
            .background(color)
    )
}

// ─── Preview ─────────────────────────────────────────────────────────────────
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MechanicHomeScreenPreview() {
    MaterialTheme {
        MechanicHomeScreen()
    }
}
