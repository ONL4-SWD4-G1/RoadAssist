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
import com.example.app_admin.complaints.view.ComplainantTechnicianScreen
import com.example.app_admin.complaints.view.ComplaintDetailsScreen
import com.example.app_admin.finance.view.FinanceScreen
import com.example.app_admin.more.view.MoreScreen
import com.example.app_admin.orders.view.OrdersScreen
import com.example.app_admin.overview.view.OverviewScreen
import com.example.app_admin.sampleComplaints
import com.example.app_admin.sampleTechnicians
import com.example.app_admin.technicians.view.TechnicianDetailScreen
import com.example.app_admin.technicians.view.TechniciansScreen
import com.example.app_admin.user.view.UserProfileScreen
import com.example.app_admin.user.view.UserSuspensionScreen
import com.example.app_admin.user.view.UserWarningScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    paddingValues: androidx.compose.foundation.layout.PaddingValues
) {
    val onNavigateToTechnician = remember(navController) {
        { id: Int -> navController.navigate(Screen.TechnicianDetails(id)) }
    }

    val onNavigateToComplaint = remember(navController) {
        { id: Int ->
            navController.navigate(Screen.ComplaintDetails(id)) {
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

        composable<Screen.TechnicianDetails> { backStackEntry ->
            val route: Screen.TechnicianDetails = backStackEntry.toRoute()

            val technician = sampleTechnicians.find { it.id == route.techId }
            val techId = backStackEntry.toRoute<Screen.TechnicianDetails>().techId

            if (technician != null) {
                TechnicianDetailScreen(
                    technician = technician,
                    onBack = { navController.popBackStack() }
                )
            }
        }

        composable<Screen.UserProfile> {
            UserProfileScreen(
                onBack = { navController.popBackStack() },
                onWarningClick = { }
            )
        }

        composable<Screen.UserWarning> { backStackEntry ->
            val route: Screen.UserWarning = backStackEntry.toRoute()

            UserWarningScreen(onBack = { navController.popBackStack() })
        }

        composable<Screen.UserSuspension> { backStackEntry ->
            val route: Screen.UserSuspension = backStackEntry.toRoute()

            UserSuspensionScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable<Screen.ComplaintDetails> { backStackEntry ->
            val route: Screen.ComplaintDetails = backStackEntry.toRoute()
            val complaint = remember(route.complaintId) {
                sampleComplaints.find { it.id == route.complaintId }
            }

            if (complaint != null) {
                ComplaintDetailsScreen(
                    complaint = complaint,
                    onBack = {
                        navController.popBackStack()
                    },
                    onNavigateToWarning = {
                        navController.navigate(Screen.UserWarning(userId = route.complaintId))
                    },
                    onNavigateToProfile = {
                        navController.navigate(Screen.UserProfile)
                    },
                    onNavigateToComplainant = {
                        navController.navigate(Screen.ComplainantTechnician(techId = route.complaintId))
                    }
                )
            }
        }

        composable<Screen.ComplainantTechnician> { backStackEntry ->
            val route: Screen.ComplainantTechnician = backStackEntry.toRoute()

            ComplainantTechnicianScreen(
                onBack = {
                    navController.popBackStack()
                },
                onNavigateToWarning = {
                    navController.navigate(Screen.UserWarning(userId = route.techId))
                },
                onNavigateToSuspension = {
                    navController.navigate(Screen.UserSuspension(userId = route.techId))
                },
                onNavigateToDelete = {
                    navController.navigate(Screen.UserProfile)
                }
            )
        }
    }
}