//package com.example.markeplaceappcompose.presentation.navigation
//
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.rememberNavController
//import com.example.markeplaceappcompose.presentation.viewmodel.product.ProductViewModel
//import com.example.markeplaceappcompose.presentation.viewmodel.product.ProductViewModelFactory
//
//fun AppNavigation(app:MarkeplaceAppCompose) {
//    val navController = rememberNavController()
//
//    val productViewModel: ProductViewModel = viewModel(
//        factory = ProductViewModelFactory(app.repository)
//    )
//  NavigationGraph(navController, productViewModel=productViewModel)
//}