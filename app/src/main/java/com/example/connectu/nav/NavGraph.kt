package com.example.connectu.nav

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.connectu.ui.screens.*
import com.example.connectu.viewmodel.UserViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun NavGraph(navController: NavHostController) {

    val userViewModel: UserViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        // -----------------------
        // LOGIN SCREEN
        // -----------------------
        composable("login") {
            LoginScreen(
                onLoginClick = { username, password ->
                    userViewModel.login(username, password)
                },
                onRegisterClick = {
                    userViewModel.clearRegisterState()
                    navController.navigate("register")
                },
                loginError = userViewModel.loginResult.value
            )

            if (userViewModel.loginResult.value == "success") {
                userViewModel.clearLoginState()
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            }
        }

        // -----------------------
        // REGISTER SCREEN
        // -----------------------
        composable("register") {
            RegisterScreen(
                onRegisterClick = { username, password, displayName ->
                    userViewModel.register(username, password, displayName)
                },
                onBackToLogin = {
                    navController.popBackStack()
                },
                registerError = if (userViewModel.registerResult.value != "success")
                    userViewModel.registerResult.value else null,
                registerSuccess = userViewModel.registerResult.value == "success"
            )
        }

        // -----------------------
        // HOME SCREEN
        // -----------------------
        composable("home") {
            HomeScreen(
                displayName = userViewModel.currentUserDisplayName.value,
                onGoToConnect = { navController.navigate("connect") },
                onGoToProfile = { navController.navigate("profile") }
            )
        }

        // -----------------------
        // CONNECT SCREEN
        // -----------------------
        composable("connect") {
            ConnectScreen()
        }

        // -----------------------
        // PROFILE SCREEN
        // -----------------------
        composable("profile") {
            ProfileScreen(
                username = userViewModel.currentUserUsername.value,
                displayName = userViewModel.currentUserDisplayName.value
            )
        }
    }
}
