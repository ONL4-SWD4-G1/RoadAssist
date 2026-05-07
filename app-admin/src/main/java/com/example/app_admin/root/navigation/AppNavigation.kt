package com.example.app_admin.root.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.example.app_admin.overview.view.OverviewScreen
import com.example.app_admin.sampleComplaints
import com.example.app_admin.sampleTechnicians
import com.example.app_admin.technicians.view.TechnicianDetailScreen
import com.example.app_admin.technicians.view.TechniciansScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    paddingValues: androidx.compose.foundation.layout.PaddingValues
) {
    val onNavigateToTechnician = remember(navController) {
        { id: Int -> navController.navigate(Screen.TechnicianDetail(id)) }
    }

    val onNavigateToComplaint = remember(navController) {
        { id: Int ->
            navController.navigate(Screen.ComplaintDetail(id)) {
                launchSingleTop = true
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Overview,
        modifier = Modifier.padding(paddingValues),
    ) {
        composable<Screen.Overview> {
            OverviewScreen(
                onNotificationClick = { }
            )
        }

        composable<Screen.Orders> {
            OrdersScreen(viewModel = viewModel())
        }

        composable<Screen.Technicians> {
            TechniciansScreen(
                onTechnicianClick = { tech -> onNavigateToTechnician(tech.id) },
                onComplaintClick = { complaint -> onNavigateToComplaint(complaint.id) },
                navController = navController
            )
        }

        composable<Screen.Finance> { FinanceScreen() }

        composable<Screen.More> { MoreScreen() }

        composable<Screen.TechnicianDetail> { backStackEntry ->
            val route: Screen.TechnicianDetail = backStackEntry.toRoute()

            val technician = sampleTechnicians.find { it.id == route.techId }

            if (technician != null) {
                TechnicianDetailScreen(
                    technician = technician,
                    onBack = { navController.popBackStack() }
                )
            }
        }

        composable<Screen.ComplaintDetail> { backStackEntry ->
            val route: Screen.ComplaintDetail = backStackEntry.toRoute()
            val complaint = remember(route.complaintId) {
                sampleComplaints.find { it.id == route.complaintId }
            }

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