package com.example.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ui.screens.AddEditScreen
import com.example.ui.screens.HomeScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object AddEdit : Screen("add_edit?id={id}") {
        fun createRoute(id: Int? = null): String {
            return if (id != null) "add_edit?id=$id" else "add_edit?id=-1"
        }
    }
}

@Composable
fun AppNavigation(viewModel: PasswordViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(
                viewModel = viewModel,
                onNavigateToAdd = { navController.navigate(Screen.AddEdit.createRoute()) },
                onNavigateToEdit = { id -> navController.navigate(Screen.AddEdit.createRoute(id)) }
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
