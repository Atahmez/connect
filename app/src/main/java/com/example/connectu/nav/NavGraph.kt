package com.example.connectu.nav

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.connectu.ui.screens.*
import com.example.connectu.viewmodel.UserViewModel

@Composable
fun NavGraph(navController: NavHostController) {

    val userViewModel: UserViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        // -----------------------
        // LOGIN
        // -----------------------
        composable("login") {
            LoginScreen(
                navController = navController,
                userViewModel = userViewModel
            )
        }

        // -----------------------
        // REGISTER
        // -----------------------
        composable("register") {
            RegisterScreen(
                navController = navController,
                userViewModel = userViewModel
            )
        }

        // -----------------------
        // HOME
        // -----------------------
        composable("home") {
            HomeScreen()
        }

        // -----------------------
        // CONNECT
        // -----------------------
        composable("connect") {
            ConnectScreen()
        }

        // -----------------------
        // PROFILE
        // -----------------------
        composable("profile") {
            ProfileScreen()
        }
    }
}
