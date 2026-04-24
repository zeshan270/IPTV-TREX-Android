package com.iptv_trex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.iptv_trex.ui.screens.LoginScreen
import com.iptv_trex.ui.screens.HomeScreen
import com.iptv_trex.ui.viewmodels.LoginViewModel

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Route.Login.route
    ) {
        composable(Route.Login.route) {
            val viewModel: LoginViewModel = hiltViewModel()
            LoginScreen(
                viewModel = viewModel,
                onLoginSuccess = {
                    navController.navigate(Route.Home.route) {
                        popUpTo(Route.Login.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Route.Home.route) {
            HomeScreen(
                navController = navController,
                onLogout = {
                    navController.navigate(Route.Login.route) {
                        popUpTo(Route.Home.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}
