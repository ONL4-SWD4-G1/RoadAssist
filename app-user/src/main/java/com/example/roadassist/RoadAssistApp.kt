package com.example.roadassist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.roadassist.auth.ui.LoginScreen
import com.example.roadassist.auth.ui.LoginViewModel
import com.example.roadassist.home.HomeScreen
import com.example.roadassist.home.HomeViewModel
import com.example.roadassist.navigation.AppDestinations

@Composable
fun RoadAssistApp(
    mainViewModel: MainViewModel = viewModel(),
) {
    val rootState by mainViewModel.rootState.collectAsStateWithLifecycle()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    LaunchedEffect(rootState.sessionLoaded, rootState.isLoggedIn, currentRoute) {
        if (!rootState.sessionLoaded) return@LaunchedEffect
        val route = currentRoute ?: return@LaunchedEffect
        when {
            rootState.isLoggedIn && route == AppDestinations.LOGIN ->
                navController.navigate(AppDestinations.HOME) {
                    popUpTo(AppDestinations.LOGIN) { inclusive = true }
                    launchSingleTop = true
                }

            !rootState.isLoggedIn && route == AppDestinations.HOME ->
                navController.navigate(AppDestinations.LOGIN) {
                    popUpTo(AppDestinations.HOME) { inclusive = true }
                    launchSingleTop = true
                }
        }
    }

    NavHost(
        navController = navController,
        startDestination = AppDestinations.SPLASH,
    ) {
        composable(AppDestinations.SPLASH) {
            SplashRoute(
                rootState = rootState,
                navController = navController,
            )
        }
        composable(AppDestinations.LOGIN) {
            val loginViewModel: LoginViewModel = viewModel()
            LoginScreen(viewModel = loginViewModel)
        }
        composable(AppDestinations.HOME) {
            val homeViewModel: HomeViewModel = viewModel()
            HomeScreen(
                viewModel = homeViewModel,
                onLogout = { mainViewModel.logout() },
            )
        }
    }
}

@Composable
private fun SplashRoute(
    rootState: RootUiState,
    navController: NavHostController,
) {
    LaunchedEffect(rootState.sessionLoaded, rootState.isLoggedIn) {
        if (!rootState.sessionLoaded) return@LaunchedEffect
        if (rootState.isLoggedIn) {
            navController.navigate(AppDestinations.HOME) {
                popUpTo(AppDestinations.SPLASH) { inclusive = true }
            }
        } else {
            navController.navigate(AppDestinations.LOGIN) {
                popUpTo(AppDestinations.SPLASH) { inclusive = true }
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
    }
}