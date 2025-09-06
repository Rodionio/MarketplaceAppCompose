package com.example.marketplaceappcompose.screens

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.marketplaceappcompose.MarketplaceAppCompose
import com.example.marketplaceappcompose.ui.navigation.BottomNavigation
import com.example.marketplaceappcompose.presentation.navigation.NavigationGraph
import com.example.marketplaceappcompose.presentation.viewmodel.product.ProductViewModel
import com.example.marketplaceappcompose.presentation.viewmodel.product.ProductViewModelFactory


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val context = LocalContext.current.applicationContext as MarketplaceAppCompose

    val productViewModel: ProductViewModel = viewModel(
        factory = ProductViewModelFactory(context.productRepository)
    )
    Scaffold(
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) { innerPadding ->
        NavigationGraph(
            productViewModel = productViewModel,
            navHostController = navController,
            innerPadding = innerPadding
        )
    }
}