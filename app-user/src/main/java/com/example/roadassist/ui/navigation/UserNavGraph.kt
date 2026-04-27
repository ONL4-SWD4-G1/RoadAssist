package com.example.roadassist.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.roadassist.ui.profile.AboutUsScreen
import com.example.roadassist.ui.profile.ContactScreen
import com.example.roadassist.ui.profile.EditProfileScreen
import com.example.roadassist.ui.profile.MySubscriptionScreen
import com.example.roadassist.ui.profile.ProfileScreen
import com.example.roadassist.ui.profile.ServiceHistoryScreen
import com.example.roadassist.ui.profile.SettingsScreen
import com.example.roadassist.ui.profile.SubscriptionPlansScreen

object Routes {

    // Onboarding & Auth
    const val SPLASH = "splash"
    const val ONBOARDING = "onboarding"
    const val SIGNUP = "signup"
    const val LOGIN = "login"
    const val OTP_VERIFY = "otp_verify"
    const val RESET_PASSWORD = "reset_password"
    const val OTP_RESET = "otp_reset"
    const val NEW_PASSWORD = "new_password"

    // Main
    const val HOME = "home"
    const val NOTIFICATIONS = "notifications"

    // Services
    const val SERVICES = "services"
    const val SERVICE_TOWING = "service_towing"
    const val SERVICE_FLAT = "service_flat_tyre"
    const val SERVICE_FUEL = "service_fuel"
    const val SERVICE_BATTERY = "service_battery"
    const val SERVICE_BRAKE = "service_brake"
    const val SERVICE_ENGINE = "service_engine"
    const val SERVICE_KEY = "service_key"
    const val SERVICE_OTHER = "service_other"

    // Booking flow
    const val DEST_LOCATION = "destination_location"
    const val LOC_CONFIRM = "location_confirmation"
    const val TECHNICIANS = "available_technicians"
    const val TECH_PROFILE = "technician_profile/{technicianId}"
    fun techProfile(id: String) = "technician_profile/$id"

    // Post-booking
    const val TRACK = "track"
    const val PAYMENT = "payment"
    const val FEEDBACK = "feedback"

    // Profile section
    const val PROFILE = "profile"
    const val EDIT_PROFILE = "edit_profile"
    const val SERVICE_HISTORY = "service_history"
    const val SUBSCRIPTION = "subscription"
    const val SUBSCRIPTION_PLANS = "subscription_plans"
    const val ABOUT_US = "about_us"
    const val CONTACT = "contact"
    const val SETTINGS = "settings"
}


@Composable
fun UserNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.SPLASH,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(Routes.PROFILE) {
            ProfileScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateTo = { route -> navController.navigate(route) },
                onNavigateToEdit = { navController.navigate(Routes.EDIT_PROFILE) },
                onLogout = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(0) { inclusive = true }
                    }
                },
            )
        }
        composable(Routes.EDIT_PROFILE) {
            EditProfileScreen(
                onNavigateBack = { navController.popBackStack() },
                onUpdated      = { navController.popBackStack() },
            )
        }

        composable(Routes.SERVICE_HISTORY) {
            ServiceHistoryScreen(onNavigateBack = { navController.popBackStack() })
        }

        composable(Routes.SUBSCRIPTION) {
            MySubscriptionScreen(
                onUpgrade      = { navController.navigate(Routes.SUBSCRIPTION_PLANS) },
                onNavigateBack = { navController.popBackStack() },
            )
        }

        composable(Routes.SUBSCRIPTION_PLANS) {
            SubscriptionPlansScreen(onNavigateBack = { navController.popBackStack() })
        }

        composable(Routes.ABOUT_US) {
            AboutUsScreen(onNavigateBack = { navController.popBackStack() })
        }

        composable(Routes.CONTACT) {
            ContactScreen(onNavigateBack = { navController.popBackStack() })
        }

        composable(Routes.SETTINGS) {
            SettingsScreen(onNavigateBack = { navController.popBackStack() })
        }
    }
}