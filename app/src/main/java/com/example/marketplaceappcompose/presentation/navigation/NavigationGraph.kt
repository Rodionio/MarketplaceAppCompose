package com.example.marketplaceappcompose.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.marketplaceappcompose.presentation.screens.AddProductScreen
import com.example.marketplaceappcompose.presentation.screens.CartScreen
import com.example.marketplaceappcompose.presentation.screens.FavoritesScreen
import com.example.marketplaceappcompose.presentation.screens.HomeScreen
import com.example.marketplaceappcompose.presentation.screens.MyListingScreen
import com.example.marketplaceappcompose.presentation.screens.ProductDetailScreen
import com.example.marketplaceappcompose.presentation.screens.ProfileScreen
import com.example.marketplaceappcompose.presentation.viewmodel.product.ProductViewModel


@Composable
fun NavigationGraph(
    navHostController: NavHostController,
    productViewModel: ProductViewModel,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navHostController,
        startDestination = "home",
        modifier = Modifier.padding(innerPadding)
    ) {
        composable("home") {
            HomeScreen(
                productViewModel = productViewModel,
                navController = navHostController
            )
        }

        composable("cart") {
            CartScreen(
                viewModel = productViewModel,
                navController = navHostController
            )
        }

        composable("profile") {
            ProfileScreen(navController = navHostController)
        }

        composable("my_listings") {
            MyListingScreen(viewModel = productViewModel, navController = navHostController)
        }

        composable("favorites") {
            FavoritesScreen(viewModel = productViewModel, navController = navHostController)
        }

        composable("add_product") {
            AddProductScreen(viewModel = productViewModel) {
                navHostController.popBackStack()
            }
        }

        composable("product_detail/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toIntOrNull()
            productId?.let {
                ProductDetailScreen(
                    productId = it,
                    viewModel = productViewModel
                )
            }
        }
    }
}