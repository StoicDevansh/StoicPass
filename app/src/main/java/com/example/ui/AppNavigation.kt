package com.example.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.data.AuthManager
import com.example.ui.screens.AddEditScreen
import com.example.ui.screens.ChangePasswordScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LoginScreen
import com.example.ui.screens.SetupScreen

sealed class Screen(val route: String) {
    object Setup : Screen("setup")
    object Login : Screen("login")
    object Home : Screen("home")
    object ChangePassword : Screen("change_password")
    object AddEdit : Screen("add_edit?id={id}") {
        fun createRoute(id: Int? = null): String {
            return if (id != null) "add_edit?id=$id" else "add_edit?id=-1"
        }
    }
}

@Composable
fun AppNavigation(viewModel: PasswordViewModel, authManager: AuthManager) {
    val navController = rememberNavController()
    val startDestination = if (authManager.isMasterPasswordSet()) Screen.Login.route else Screen.Setup.route

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.Setup.route) {
            SetupScreen(
                authManager = authManager,
                onSetupComplete = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Setup.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Login.route) {
            LoginScreen(
                authManager = authManager,
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                viewModel = viewModel,
                onNavigateToAdd = { navController.navigate(Screen.AddEdit.createRoute()) },
                onNavigateToEdit = { id -> navController.navigate(Screen.AddEdit.createRoute(id)) },
                onNavigateToSettings = { navController.navigate(Screen.ChangePassword.route) }
            )
        }
        composable(Screen.ChangePassword.route) {
            ChangePasswordScreen(
                authManager = authManager,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(
            route = Screen.AddEdit.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType; defaultValue = -1 })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: -1
            AddEditScreen(
                viewModel = viewModel,
                passwordId = if (id == -1) null else id,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
