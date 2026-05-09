package com.example.roadassist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.roadassist.features.home.home.view.HomeScreen
import com.example.roadassist.features.home.notification.view.NotificationsScreen
import com.example.roadassist.features.onboarding.view.OnboardingScreen
import com.example.roadassist.features.services.otherservices.view.OtherServiceFormScreen
import com.example.roadassist.features.services.services.view.ServicesScreen
import com.example.roadassist.features.signup.otp.view.OtpScreen
import com.example.roadassist.features.signup.otp.view.SuccessDialog
import com.example.roadassist.features.signup.signup.view.SignupScreen
import com.example.roadassist.features.splash.view.SplashScreen

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
        _root_ide_package_.com.example.roadassist.features.login.creatnewpassword.view.PasswordUpdatedDialog(
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
            _root_ide_package_.com.example.roadassist.features.login.login.view.LoginScreen(
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
            _root_ide_package_.com.example.roadassist.features.login.resetpassword.view.ResetPasswordScreen(
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
            _root_ide_package_.com.example.roadassist.features.login.creatnewpassword.view.CreateNewPasswordScreen(
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
                onServicesClick = { navController.navigate(Routes.SERVICES) },
                onClickAction = { navController.navigate(Routes.PROFILE) }
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
                onBackClick = { navController.popBackStack() },
                onOtherServiceClick = { navController.navigate(Routes.OTHER_SERVICE) },
            )
        }

        // Other Service
        composable(route = Routes.OTHER_SERVICE) {
            OtherServiceFormScreen(
                onBackClick = { navController.popBackStack() }
            )
        }


    }
}
