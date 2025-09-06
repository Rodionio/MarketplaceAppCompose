package com.example.markeplaceappcompose.screens

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.markeplaceappcompose.MarkeeplaceAppCompose
import com.example.markeplaceappcompose.ui.navigation.BottomNavigation
import com.example.markeplaceappcompose.presentation.navigation.NavigationGraph
import com.example.markeplaceappcompose.presentation.viewmodel.product.ProductViewModel
import com.example.markeplaceappcompose.presentation.viewmodel.product.ProductViewModelFactory


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val context = LocalContext.current.applicationContext as MarkeeplaceAppCompose

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