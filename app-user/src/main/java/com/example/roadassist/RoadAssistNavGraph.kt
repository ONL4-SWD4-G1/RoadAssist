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
    const val HOME = "home"
}

@Composable
fun RoadAssistNavGraph() {

    val navController = rememberNavController()

    var showAccountCreatedDialog by remember { mutableStateOf(false) }
    var showPasswordUpdatedDialog by remember { mutableStateOf(false) }

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

        // ── Signup ──
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

        // ── OTP (Signup) ──
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

    }
}
