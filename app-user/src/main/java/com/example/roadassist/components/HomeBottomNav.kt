package com.example.roadassist.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Construction
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MyLocation
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roadassist.navigation.Routes
import com.example.roadassist.theme.NavyBlue

data class BottomNavItem(val label: String, val icon: ImageVector, val route: String)

val bottomNavItems = listOf(
    BottomNavItem("Home", Icons.Outlined.Home, Routes.HOME),
    BottomNavItem("Services", Icons.Outlined.Construction, Routes.SERVICES),
    BottomNavItem("Track", Icons.Outlined.MyLocation, Routes.TRACK),
    BottomNavItem("Help", Icons.Outlined.Phone, Routes.CONTACT),
)

@Composable
fun HomeBottomNav(selected: String, onNavigate: (String) -> Unit) {
    NavigationBar(containerColor = Color.White, tonalElevation = 4.dp) {
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                selected = selected == item.route,
                onClick = { onNavigate(item.route) },
                icon = { Icon(item.icon, item.label) },
                label = { Text(item.label, fontSize = 11.sp) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = NavyBlue,
                    selectedTextColor = NavyBlue,
                    unselectedIconColor = Color(0xFF9CA3AF),
                    unselectedTextColor = Color(0xFF9CA3AF),
                    indicatorColor = Color.Transparent,
                ),
            )
        }
    }
}