package com.example.roadassist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.roadassist.home.home.view.HomeScreen
import com.example.roadassist.home.notification.view.NotificationsScreen
import com.example.roadassist.login.creatnewpassword.view.CreateNewPasswordScreen
import com.example.roadassist.login.login.view.LoginScreen
import com.example.roadassist.login.creatnewpassword.view.PasswordUpdatedDialog
import com.example.roadassist.login.resetpassword.view.ResetPasswordScreen
import com.example.roadassist.onboarding.view.OnboardingScreen
import com.example.roadassist.profile.AboutUsScreen
import com.example.roadassist.profile.ContactScreen
import com.example.roadassist.profile.EditProfileScreen
import com.example.roadassist.profile.MySubscriptionScreen
import com.example.roadassist.profile.ProfileScreen
import com.example.roadassist.profile.ServiceHistoryScreen
import com.example.roadassist.profile.SettingsScreen
import com.example.roadassist.profile.SubscriptionPlansScreen
import com.example.roadassist.services.otherservices.view.OtherServiceFormScreen
import com.example.roadassist.services.services.view.ServicesScreen
import com.example.roadassist.signup.otp.view.OtpScreen
import com.example.roadassist.signup.signup.view.SignupScreen
import com.example.roadassist.signup.otp.view.SuccessDialog
import com.example.roadassist.splash.view.SplashScreen

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
