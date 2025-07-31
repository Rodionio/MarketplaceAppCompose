package com.example.markeplaceappcompose.screens

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.markeplaceappcompose.presentation.navigation.BottomNavigation
import com.example.markeplaceappcompose.presentation.navigation.NavigationGraph


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(navController=navController)
        }
    ) {
        NavigationGraph(navHostController = navController)
    }
}