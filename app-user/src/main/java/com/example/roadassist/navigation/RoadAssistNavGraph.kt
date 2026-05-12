package com.example.roadassist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.roadassist.features.booking.feedback.view.FeedbackScreen
import com.example.roadassist.features.booking.payment.view.PaymentScreen
import com.example.roadassist.features.booking.technicians.availabletechs.view.AvailableTechniciansScreen
import com.example.roadassist.features.booking.technicians.profile.view.TechnicianProfileScreen
import com.example.roadassist.features.booking.tracking.view.ServiceTrackingScreen
import com.example.roadassist.features.home.home.view.HomeScreen
import com.example.roadassist.features.home.notification.view.NotificationsScreen
import com.example.roadassist.features.login.creatnewpassword.view.CreateNewPasswordScreen
import com.example.roadassist.features.login.login.view.LoginScreen
import com.example.roadassist.features.login.resetpassword.view.ResetPasswordScreen
import com.example.roadassist.features.onboarding.view.OnboardingScreen
import com.example.roadassist.features.profile.about.view.AboutUsScreen
import com.example.roadassist.features.profile.contact.view.ContactScreen
import com.example.roadassist.features.profile.edit.view.EditProfileScreen
import com.example.roadassist.features.profile.history.view.ServiceHistoryScreen
import com.example.roadassist.features.profile.settings.view.SettingsScreen
import com.example.roadassist.features.profile.subscription.view.MySubscriptionScreen
import com.example.roadassist.features.profile.subscriptionplans.view.SubscriptionPlansScreen
import com.example.roadassist.features.profile.userprofile.view.ProfileScreen
import com.example.roadassist.features.services.battery.view.BatteryServiceScreen
import com.example.roadassist.features.services.brake.view.BrakeServiceScreen
import com.example.roadassist.features.services.engine.view.EngineServiceScreen
import com.example.roadassist.features.services.flattyre.view.FlatTyreServiceScreen
import com.example.roadassist.features.services.fuel.view.FuelServiceScreen
import com.example.roadassist.features.services.keyretrieval.view.KeyRetrievalServiceScreen
import com.example.roadassist.features.services.location.view.LocationConfirmationScreen
import com.example.roadassist.features.services.location.view.MapPickerScreen
import com.example.roadassist.features.services.otherservices.view.OtherServiceFormScreen
import com.example.roadassist.features.services.services.view.ServicesScreen
import com.example.roadassist.features.services.towing.view.TowingServiceScreen
import com.example.roadassist.features.signup.otp.view.OtpScreen
import com.example.roadassist.features.signup.signup.view.SignupScreen
import com.example.roadassist.features.splash.view.SplashScreen

@Composable
fun RoadAssistNavGraph() {

    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {
        // Splash Screen
        composable(Routes.SPLASH) {
            SplashScreen(
                onNavigateToOnboarding = {
                    navController.navigate(Routes.ONBOARDING) {
                        popUpTo(Routes.SPLASH) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // OnBoarding Screen
        composable(Routes.ONBOARDING) {
            OnboardingScreen(
                onFinish = {
                    navController.navigate(Routes.SIGNUP) {
                        popUpTo(Routes.ONBOARDING) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // Signup
        composable(Routes.SIGNUP) {
            SignupScreen(
                onSignupSuccess = {
                    navController.navigate(Routes.OTP_SIGNUP)
                },
                onLoginClick = {
                    navController.navigate(Routes.LOGIN)
                }
            )
        }

        // OTP (Signup)
        composable(Routes.OTP_SIGNUP) {
            OtpScreen(
                onVerifySuccess = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.SIGNUP) { inclusive = true }
                    }
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        // Login
        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) {
                            inclusive = true
                        }
                    }
                },
                onSignupClick = {
                    navController.navigate(Routes.SIGNUP)
                },
                onForgotPasswordClick = {
                    navController.navigate(Routes.RESET_PASSWORD)
                }
            )
        }

        // Reset Password
        composable(Routes.RESET_PASSWORD) {
            ResetPasswordScreen(
                onGetOtpClick = {
                    navController.navigate(Routes.OTP_RESET)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // OTP (Reset Password)
        composable(Routes.OTP_RESET) {
            OtpScreen(
                onVerifySuccess = {
                    navController.navigate(Routes.CREATE_NEW_PASSWORD)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // Create New Password
        composable(Routes.CREATE_NEW_PASSWORD) {
            CreateNewPasswordScreen(
                onUpdateClick = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.RESET_PASSWORD) { inclusive = true }
                    }
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        // Home
        composable(Routes.HOME) {
            HomeScreen(
                onNavigateToNotifications = { navController.navigate(Routes.NOTIFICATIONS) },
                onNavigateToService = { route -> navController.navigate(route) },
                onNavigateToServices = { navController.navigate(Routes.SERVICES) },
                onNavigateToTrack = { navController.navigate(Routes.TRACK) },
                onNavigateToHelp = { navController.navigate(Routes.CONTACT) },
                onNavigateToProfile = { navController.navigate(Routes.PROFILE) },
            )
        }


        // Notification
        composable(route = Routes.NOTIFICATIONS) {
            NotificationsScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        // Services
        composable(Routes.SERVICES) {
            ServicesScreen(
                onServiceSelected = { route -> navController.navigate(route) },
                onNavigateBack = { navController.popBackStack() },
                onNavigateToTrack = { navController.navigate(Routes.TRACK) },
                onNavigateToHelp = { navController.navigate(Routes.CONTACT) },
            )
        }

        composable(Routes.SERVICE_TOWING) {
            TowingServiceScreen(
                onNavigateBack = { navController.popBackStack() },
                onBookService = { navController.navigate(Routes.mapPicker(Routes.ServiceIds.TOWING)) },
                onSelectMapClicked = { navController.navigate(Routes.mapPicker(Routes.ServiceIds.TOWING)) },

                )
        }

        composable(Routes.SERVICE_FLAT) {
            FlatTyreServiceScreen(
                onNavigateBack = { navController.popBackStack() },
                onBookService = { navController.navigate(Routes.mapPicker(Routes.ServiceIds.FLAT_TYRE)) },
            )
        }

        composable(Routes.SERVICE_FUEL) {
            FuelServiceScreen(
                onNavigateBack = { navController.popBackStack() },
                onBookService = { navController.navigate(Routes.mapPicker(Routes.ServiceIds.FUEL)) },
            )
        }
        composable(Routes.SERVICE_KEY) {
            KeyRetrievalServiceScreen(
                onNavigateBack = { navController.popBackStack() },
                onBookService = { navController.navigate(Routes.mapPicker(Routes.ServiceIds.KEY)) },
            )
        }
        composable(Routes.SERVICE_BATTERY) {
            BatteryServiceScreen(
                onBookService = { navController.navigate(Routes.mapPicker(Routes.ServiceIds.BATTERY)) },
                onNavigateBack = { navController.popBackStack() },
            )
        }

        composable(Routes.SERVICE_BRAKE) {
            BrakeServiceScreen(
                onBookService = { navController.navigate(Routes.mapPicker(Routes.ServiceIds.BRAKE)) },
                onNavigateBack = { navController.popBackStack() },
            )
        }

        composable(Routes.SERVICE_ENGINE) {
            EngineServiceScreen(
                onBookService = { navController.navigate(Routes.mapPicker(Routes.ServiceIds.ENGINE)) },
                onNavigateBack = { navController.popBackStack() },
            )
        }

        composable(route = Routes.OTHER_SERVICE) {
            OtherServiceFormScreen(
                onBookService = { navController.navigate(Routes.mapPicker(Routes.ServiceIds.OTHERS)) },
                onBackClick = { navController.popBackStack() }
            )
        }

        // Profile
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
                onUpdated = { navController.popBackStack() },
            )
        }

        composable(Routes.SERVICE_HISTORY) {
            ServiceHistoryScreen(onNavigateBack = { navController.popBackStack() })
        }

        composable(Routes.SUBSCRIPTION) {
            MySubscriptionScreen(
                onUpgrade = { navController.navigate(Routes.SUBSCRIPTION_PLANS) },
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
            ContactScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToHome = { navController.navigate(Routes.HOME) },
                onNavigateToServices = { navController.navigate(Routes.SERVICES) },
                onNavigateToTrack = { navController.navigate(Routes.TRACK) },
            )
        }

        composable(Routes.SETTINGS) {
            SettingsScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToChangePassword = { navController.navigate(Routes.CREATE_NEW_PASSWORD) },
                onNavigateToFeedback = { navController.navigate(Routes.FEEDBACK) }
            )
        }

        composable(Routes.TRACK) { backStackEntry ->
            val technicianId = backStackEntry.arguments?.getString("technicianId") ?: ""
            val serviceId = backStackEntry.arguments?.getString("serviceId") ?: "others"

            ServiceTrackingScreen(
                technicianId = technicianId,
                onMakePayment = { navController.navigate(Routes.payment(serviceId, technicianId)) },
                onNavigateBack = { navController.popBackStack() },
                onNavigateToHome = { navController.navigate(Routes.HOME) },
                onNavigateToServices = { navController.navigate(Routes.SERVICES) },
                onNavigateToHelp = { navController.navigate(Routes.CONTACT) },
            )
        }
        // ── Booking flow ──────────────────────────────────────
        composable(Routes.MAP_PICKER) { backStackEntry ->
            val serviceId =
                backStackEntry.arguments?.getString("serviceId") ?: Routes.ServiceIds.OTHERS
            MapPickerScreen(
                onLocationSelected = { navController.navigate(Routes.locConfirm(serviceId)) },
                onNavigateBack = { navController.popBackStack() },
            )
        }

        composable(Routes.LOC_CONFIRM) { backStackEntry ->
            val serviceId =
                backStackEntry.arguments?.getString("serviceId") ?: Routes.ServiceIds.OTHERS
            LocationConfirmationScreen(
                onConfirm = { navController.navigate(Routes.technicians(serviceId)) },
                onNavigateBack = { navController.popBackStack() },
            )
        }

        composable(Routes.TECHNICIANS) { backStackEntry ->
            val serviceId =
                backStackEntry.arguments?.getString("serviceId") ?: Routes.ServiceIds.OTHERS
            AvailableTechniciansScreen(
                onSelectTechnician = { id ->
                    navController.navigate(Routes.techProfile(serviceId, id))
                },
                onNavigateBack = { navController.popBackStack() },
            )
        }
        composable(Routes.TECH_PROFILE) { backStackEntry ->
            val serviceId = backStackEntry.arguments?.getString("serviceId") ?: "others"
            val technicianId = backStackEntry.arguments?.getString("technicianId") ?: ""
            TechnicianProfileScreen(
                technicianId = technicianId,
                onConfirm = { navController.navigate(Routes.track(serviceId, technicianId)) },
                onNavigateBack = { navController.popBackStack() },
            )
        }

        composable(Routes.PAYMENT) { backStackEntry ->
            val serviceId = backStackEntry.arguments?.getString("serviceId") ?: "others"
            val technicianId = backStackEntry.arguments?.getString("technicianId") ?: ""
            PaymentScreen(
                serviceId = serviceId,
                technicianId = technicianId,
                onPaymentSuccess = { navController.navigate(Routes.FEEDBACK) },
                onNavigateBack = { navController.popBackStack() },
            )
        }

        composable(Routes.FEEDBACK) {
            FeedbackScreen(
                onSubmit = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.HOME) { inclusive = true }
                    }
                },
                onNavigateBack = { navController.popBackStack() },
            )
        }
    }
}