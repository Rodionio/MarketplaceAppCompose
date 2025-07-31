package com.example.markeplaceappcompose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.markeplaceappcompose.presentation.screens.CartScreen
import com.example.markeplaceappcompose.presentation.screens.HomeScreen
import com.example.markeplaceappcompose.presentation.screens.ProfileScreen
import com.example.markeplaceappcompose.presentation.screens.SearchScreen
import com.example.markeplaceappcompose.presentation.screens.SettingsScreen



@Composable
fun NavigationGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = "home") {
        composable("home") {
            HomeScreen()
        }
        composable("search") {
            SearchScreen()

        }
        composable("cart") {
            CartScreen()
        }
        composable("profile") {
            ProfileScreen()
        }
        composable("settings") {
            SettingsScreen()
        }

    }
}