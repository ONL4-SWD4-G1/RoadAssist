package com.example.earningsdashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Engineering
import androidx.compose.material.icons.outlined.HeadsetMic
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material.icons.outlined.Work
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.darkColorScheme
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_mechanic.AccentBlue
import com.example.app_mechanic.CardDark
import com.example.app_mechanic.GoldStar
import com.example.app_mechanic.NavItem
import com.example.app_mechanic.NavSelected
import com.example.app_mechanic.SurfaceDark
import com.example.app_mechanic.TextMuted
import com.example.app_mechanic.TextPrimary
import com.example.app_mechanic.TextSecondary

// ─── Colors ───────────────────────────────────────────────────────────────────
private val BgDark         = Color(0xFF0D1117)
private val SurfaceDark3    = Color(0xFF161B22)
private val CardDark3       = Color(0xFF1C2333)
private val AccentBlue3     = Color(0xFF2F81F7)
private val TextPrimary3    = Color(0xFFE6EDF3)
private val TextSecondary3  = Color(0xFF8B949E)
private val TextMuted3      = Color(0xFF656D76)
private val GoldStar3       = Color(0xFFF0C048)
private val DividerColor   = Color(0xFF21262D)
private val RedLogout      = Color(0xFF8B1A1A)
private val RedLogoutText  = Color(0xFFE05C5C)
private val NavSelected3    = Color(0xFF2F81F7)
private val ToggleOn       = Color(0xFF2563EB)
private val AvatarBorder   = Color(0xFF2F81F7)

// ─── Nav Items ────────────────────────────────────────────────────────────────
data class NavItem3(val label: String, val icon: ImageVector, val selected: Boolean = false)
private val profileNavItems = listOf(
    NavItem("JOBS", Icons.Outlined.Work),
    NavItem("EARNINGS", Icons.Outlined.AccountBalanceWallet),
    NavItem("PROFILE", Icons.Outlined.Person, selected = true),
    NavItem("SUPPORT", Icons.Outlined.HeadsetMic),
)

// ─── Main Screen ─────────────────────────────────────────────────────────────
@Composable
fun ProfileScreen() {
    var notificationsOn by remember { mutableStateOf(true) }

    Scaffold(
        containerColor = BgDark,
        topBar         = { ProfileTopBar() },
        bottomBar      = { ProfileBottomNav() }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(24.dp))

            // ── Avatar ───────────────────────────────────────────────────────
            AvatarSection()

            Spacer(Modifier.height(14.dp))

            // ── Name & Title ─────────────────────────────────────────────────
            Text(
                text       = "Alex Johnson",
                color      = TextPrimary,
                fontSize   = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text     = "Professional Master Mechanic",
                color    = AccentBlue,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(Modifier.height(8.dp))

            // ── Rating ───────────────────────────────────────────────────────
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Filled.Star,
                    contentDescription = null,
                    tint     = GoldStar,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text       = "4.8",
                    color      = TextPrimary,
                    fontSize   = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text     = "(124 Reviews)",
                    color    = TextSecondary,
                    fontSize = 13.sp
                )
            }

            Spacer(Modifier.height(28.dp))

            // ── Sections ─────────────────────────────────────────────────────
            ProfileGroup(label = "PERSONAL INFORMATION") {
                InfoRow(icon = Icons.Outlined.Person,     label = "Full Name",        value = "Alex Johnson")
                RowDivider()
                InfoRow(icon = Icons.Outlined.Phone,      label = "Phone Number",     value = "+1 (555) 012-3456")
                RowDivider()
                InfoRow(icon = Icons.Outlined.Engineering,label = "Specialization",   value = "Diesel Engines, Roadside Recovery")
            }

            Spacer(Modifier.height(20.dp))

            ProfileGroup(label = "SERVICE DETAILS") {
                ClickableRow(icon = Icons.Outlined.DirectionsCar, title = "Vehicle Info")
                RowDivider()
                ClickableRow(icon = Icons.Outlined.AccountBalanceWallet, title = "Payout Settings")
            }

            Spacer(Modifier.height(20.dp))

            ProfileGroup(label = "PREFERENCES") {
                ToggleRow(
                    icon    = Icons.Outlined.NotificationsNone,
                    title   = "Notifications",
                    checked = notificationsOn,
                    onToggle = { notificationsOn = it }
                )
                RowDivider()
                ClickableRow(icon = Icons.Outlined.Shield, title = "Privacy & Security")
            }

            Spacer(Modifier.height(24.dp))

            // ── Logout Button ─────────────────────────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(RedLogout),
                contentAlignment = Alignment.Center
            ) {
                TextButton(
                    onClick  = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Icon(
                        Icons.Outlined.Logout,
                        contentDescription = null,
                        tint     = RedLogoutText,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text       = "Logout Account",
                        color      = RedLogoutText,
                        fontSize   = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            Text(
                text     = "App Version 2.4.0 (120)",
                color    = TextMuted,
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(16.dp))
        }
    }
}

// ─── Avatar Section ───────────────────────────────────────────────────────────
@Composable
fun AvatarSection() {
    Box(contentAlignment = Alignment.BottomEnd) {
        // Gradient ring border
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(
                    width = 3.dp,
                    brush = Brush.linearGradient(listOf(AccentBlue, Color(0xFF1D3A6E))),
                    shape = CircleShape
                )
                .padding(4.dp)
                .clip(CircleShape)
                .background(Color(0xFF2A3A5C)),
            contentAlignment = Alignment.Center
        ) {
            // Avatar placeholder — replace with AsyncImage(coil) for real photo
            Text(
                text       = "AJ",
                color      = Color.White,
                fontSize   = 28.sp,
                fontWeight = FontWeight.Bold
            )
        }
        // Edit badge
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(AccentBlue)
                .border(2.dp, BgDark, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Outlined.Edit,
                contentDescription = "Edit photo",
                tint     = Color.White,
                modifier = Modifier.size(14.dp)
            )
        }
    }
}

// ─── Profile Group (card with label) ─────────────────────────────────────────
@Composable
fun ProfileGroup(label: String, content: @Composable ColumnScope.() -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text          = label,
            color         = TextMuted,
            fontSize      = 11.sp,
            fontWeight    = FontWeight.Bold,
            letterSpacing = 1.4.sp,
            modifier      = Modifier.padding(bottom = 8.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(CardDark)
        ) {
            content()
        }
    }
}

// ─── Info Row (label + value, no chevron interaction) ─────────────────────────
@Composable
fun InfoRow(icon: ImageVector, label: String, value: String) {
    Row(
        modifier          = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(20.dp))
        Spacer(Modifier.width(14.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(label, color = TextMuted,    fontSize = 11.sp)
            Spacer(Modifier.height(2.dp))
            Text(value, color = TextPrimary,  fontSize = 14.sp, fontWeight = FontWeight.Medium)
        }
        Icon(Icons.Outlined.ChevronRight, contentDescription = null, tint = TextMuted, modifier = Modifier.size(20.dp))
    }
}

// ─── Clickable Row ────────────────────────────────────────────────────────────
@Composable
fun ClickableRow(icon: ImageVector, title: String) {
    Row(
        modifier          = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier         = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFF1A2A4A)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = AccentBlue, modifier = Modifier.size(18.dp))
        }
        Spacer(Modifier.width(14.dp))
        Text(title, color = TextPrimary, fontSize = 15.sp, modifier = Modifier.weight(1f))
        Icon(Icons.Outlined.ChevronRight, contentDescription = null, tint = TextMuted, modifier = Modifier.size(20.dp))
    }
}

// ─── Toggle Row ───────────────────────────────────────────────────────────────
@Composable
fun ToggleRow(icon: ImageVector, title: String, checked: Boolean, onToggle: (Boolean) -> Unit) {
    Row(
        modifier          = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier         = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFF1A2A4A)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = AccentBlue, modifier = Modifier.size(18.dp))
        }
        Spacer(Modifier.width(14.dp))
        Text(title, color = TextPrimary, fontSize = 15.sp, modifier = Modifier.weight(1f))
        Switch(
            checked         = checked,
            onCheckedChange = onToggle,
            colors          = SwitchDefaults.colors(
                checkedThumbColor       = Color.White,
                checkedTrackColor       = ToggleOn,
                uncheckedThumbColor     = TextMuted,
                uncheckedTrackColor     = CardDark,
                uncheckedBorderColor    = DividerColor
            )
        )
    }
}

// ─── Thin Divider ────────────────────────────────────────────────────────────
@Composable
fun RowDivider() {
    HorizontalDivider(
        modifier  = Modifier.padding(start = 50.dp),
        color     = DividerColor,
        thickness = 0.5.dp
    )
}

// ─── Top Bar ──────────────────────────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopBar() {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Outlined.ArrowBackIos, contentDescription = "Back",
                    tint = TextPrimary, modifier = Modifier.size(20.dp))
            }
        },
        title = {
            Text("Profile", color = TextPrimary, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Outlined.Settings, contentDescription = "Settings",
                    tint = TextPrimary, modifier = Modifier.size(22.dp))
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = BgDark)
    )
}

// ─── Bottom Nav ───────────────────────────────────────────────────────────────
@Composable
fun ProfileBottomNav() {
    NavigationBar(containerColor = SurfaceDark, tonalElevation = 0.dp) {
        profileNavItems.forEach { item ->
            NavigationBarItem(
                selected = item.selected,
                onClick  = {},
                icon     = { Icon(item.icon, contentDescription = item.label, modifier = Modifier.size(22.dp)) },
                label    = { Text(item.label, fontSize = 9.sp, fontWeight = FontWeight.SemiBold, letterSpacing = 0.5.sp) },
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

// ─── Preview ─────────────────────────────────────────────────────────────────
@Preview(showBackground = true, backgroundColor = 0xFF0D1117, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    MaterialTheme(colorScheme = darkColorScheme()) {
        ProfileScreen()
    }
}