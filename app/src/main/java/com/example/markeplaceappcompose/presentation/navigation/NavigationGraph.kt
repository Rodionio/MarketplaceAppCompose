package com.example.markeplaceappcompose.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.markeplaceappcompose.presentation.screens.AddProductScreen
import com.example.markeplaceappcompose.presentation.screens.CartScreen
import com.example.markeplaceappcompose.presentation.screens.FavoritesScreen
import com.example.markeplaceappcompose.presentation.screens.HomeScreen
import com.example.markeplaceappcompose.presentation.screens.MyListingScreen
import com.example.markeplaceappcompose.presentation.screens.ProfileScreen
import com.example.markeplaceappcompose.presentation.screens.SearchScreen
import com.example.markeplaceappcompose.presentation.viewmodel.product.ProductViewModel


@Composable
fun NavigationGraph(
    navHostController: NavHostController,
    productViewModel: ProductViewModel
) {
    NavHost(navController = navHostController,startDestination = "home") {
        composable("home") {
            HomeScreen(
                productViewModel = productViewModel,
                onAddProductClick = { navHostController.navigate("add_product") }
            )
        }
        composable("search") {
            SearchScreen()

        }
        composable("cart") {
            CartScreen()
        }
        composable("profile") {
            ProfileScreen(navController = navHostController)
        }

        composable("add_product") {
            AddProductScreen(
                viewModel = productViewModel
            ) {
                navHostController.popBackStack()
            }
        }

        composable("my_listings") {
            MyListingScreen(viewModel = productViewModel)
        }

        composable("favorites") {
            FavoritesScreen(viewModel = productViewModel)

        }

    }
}