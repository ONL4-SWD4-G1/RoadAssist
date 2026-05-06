package com.example.app_admin.root.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.app_admin.complaints.view.ComplaintDetailsScreen
import com.example.app_admin.finance.view.FinanceScreen
import com.example.app_admin.more.view.MoreScreen
import com.example.app_admin.orders.view.OrdersScreen
import com.example.app_admin.sampleComplaints
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
            OrdersScreen(viewModel = viewModel())
        }

        composable<Screen.Technicians> {
            TechniciansScreen(
                onTechnicianClick = { tech ->
                    navController.navigate(Screen.TechnicianDetail(tech.id))
                },
                onComplaintClick = { complaint ->
                    navController.navigate(Screen.ComplaintDetail(complaint.id))
                },
                navController = navController
            )
        }

        composable<Screen.Finance> { FinanceScreen() }

        composable<Screen.More> { MoreScreen() }

        // Example of detail navigation
        composable<Screen.TechnicianDetail> { backStackEntry ->
            // In a real app, use the ID to fetch from ViewModel
            Text("تفاصيل الفني")
        }

        // AppNavigation.kt

        composable<Screen.ComplaintDetail> { backStackEntry ->
            val route: Screen.ComplaintDetail = backStackEntry.toRoute()
            val complaint = sampleComplaints.find { it.id == route.complaintId }

            if (complaint != null) {
                ComplaintDetailsScreen(
                    complaint = complaint,
                    onBack = { navController.popBackStack() },
                    onNavigateToWarning = { },
                    onNavigateToProfile = { }
                )
            }
        }
    }
}