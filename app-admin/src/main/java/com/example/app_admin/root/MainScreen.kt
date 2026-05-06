package com.example.app_admin.root

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Engineering
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.app_admin.root.navigation.AppNavigation
import com.example.app_admin.root.navigation.Screen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        containerColor = Color(0xffF8F7F6),
        bottomBar = {
            Column {
                HorizontalDivider(color = Color(0xFFE2E8F0), thickness = 1.dp)
                NavigationBar(containerColor = Color.White) {
                    val items = listOf(
                        NavigationItem(label = "نظرة عامة", icon = Icons.Default.GridView, route = Screen.Overview),
                        NavigationItem(label = "الطلبات", icon = Icons.Default.Assignment, route = Screen.Orders),
                        NavigationItem(label = "الفنيين", icon = Icons.Default.Engineering, route = Screen.Technicians),
                        NavigationItem(label = "المالية", icon = Icons.Default.AttachMoney, route = Screen.Finance),
                        NavigationItem(label = "المزيد", icon = Icons.Default.MoreHoriz, route = Screen.More)
                    )

                    items.forEach { item ->
                        val isSelected = currentDestination?.hierarchy?.any {
                            it.hasRoute(item.route::class)
                        } == true

                        NavigationBarItem(
                            selected = isSelected,
                            onClick = {
                                navController.navigate(item.route) {
                                    // Pop up to the start destination to avoid building a huge stack
                                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(item.icon, contentDescription = null) },
                            label = { Text(item.label, fontSize = 10.sp) }
                        )
                    }
                }
            }
        }
    ) { padding ->
        AppNavigation(navController = navController, paddingValues = padding)
    }
}

data class NavigationItem(
    val label: String,
    val icon: ImageVector,
    val route: Any
)