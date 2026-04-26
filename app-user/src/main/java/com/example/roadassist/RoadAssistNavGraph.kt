package com.example.roadassist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

object Routes {
    const val SPLASH = "splash"
    const val ONBOARDING = "onboarding"
    const val SIGNUP = "signup"
    const val LOGIN = "login"
    const val OTP_SIGNUP = "otp_signup"
    const val RESET_PASSWORD = "reset_password"
    const val OTP_RESET = "otp_reset"
    const val CREATE_NEW_PASSWORD = "create_new_password"
    const val HOME = "home"
    const val SERVICES= "services"
    const val OTHER_SERVICE = "other_service"
    const val NOTIFICATIONS = "notifications"
}

@Composable
fun RoadAssistNavGraph() {

    val navController = rememberNavController()

    var showAccountCreatedDialog by remember { mutableStateOf(false) }
    var showPasswordUpdatedDialog by remember { mutableStateOf(false) }

    // Account Create Dialog
    if (showAccountCreatedDialog) {
        SuccessDialog(
            onExploreClick = {
                showAccountCreatedDialog = false
                navController.navigate(Routes.HOME) {
                    popUpTo(Routes.SIGNUP) {
                        inclusive = true
                    }
                }
            }
        )
    }

    // Password Updated Dialog
    if (showPasswordUpdatedDialog) {
        PasswordUpdatedDialog(
            onExploreClick = {
                showPasswordUpdatedDialog = false
                navController.navigate(Routes.LOGIN) {
                    popUpTo(Routes.RESET_PASSWORD) {
                        inclusive = true
                    }
                }
            }
        )
    }

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
                    showAccountCreatedDialog = true
                },
                onBackClick = {
                    navController.popBackStack()
                }
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
                    showPasswordUpdatedDialog = true
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // Home
        composable(route = Routes.HOME) {
            HomeScreen(
                navController = navController,
                onNotificationClick = { navController.navigate(Routes.NOTIFICATIONS) },
                onServicesClick = { navController.navigate(Routes.SERVICES) }
            )
        }

        // Notification
        composable(route = Routes.NOTIFICATIONS) {
            NotificationsScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        // Services
        composable(route = Routes.SERVICES) {
            ServicesScreen(
                navController = navController,
                onBackClick   = { navController.popBackStack() }
            )
        }

        // OtherServices
        composable(route = Routes.OTHER_SERVICE) {
            OtherServiceFormScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }




    }
}
