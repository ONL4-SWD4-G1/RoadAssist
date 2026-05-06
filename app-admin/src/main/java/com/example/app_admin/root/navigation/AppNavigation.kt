package com.example.app_admin.root.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.app_admin.finance.view.FinanceScreen
import com.example.app_admin.more.view.MoreScreen
import com.example.app_admin.orders.view.OrdersScreen
import com.example.app_admin.technicians.view.TechniciansScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    paddingValues: androidx.compose.foundation.layout.PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Technicians,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable<Screen.Overview> { Text("نظرة عامة") }

        composable<Screen.Orders> {
            // ViewModel is automatically scoped to this destination
            OrdersScreen(viewModel = viewModel())
        }

        composable<Screen.Technicians> {
            TechniciansScreen(
                onTechnicianClick = { tech ->
                    navController.navigate(Screen.TechnicianDetail(tech.id))
                },
                onComplaintClick = { complaint ->
                    navController.navigate(Screen.ComplaintDetail(complaint.id))
                }
            )
        }

        composable<Screen.Finance> { FinanceScreen() }

        composable<Screen.More> { MoreScreen() }

        // Example of detail navigation
        composable<Screen.TechnicianDetail> { backStackEntry ->
            // In a real app, use the ID to fetch from ViewModel
            Text("تفاصيل الفني")
        }
    }
}