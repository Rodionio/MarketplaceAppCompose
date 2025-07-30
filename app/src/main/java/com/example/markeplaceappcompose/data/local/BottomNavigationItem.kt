package com.example.markeplaceappcompose.data.local


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationItem(
    val title: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector,
    val route: String
){
    object Home : BottomNavigationItem("Home", Icons.Outlined.Home, Icons.Filled.Home,"home")
    object Search : BottomNavigationItem("Search", Icons.Outlined.Search, Icons.Filled.Search,"search")
    object Cart : BottomNavigationItem("Cart", Icons.Outlined.ShoppingCart, Icons.Filled.ShoppingCart, "cart")
    object Profile : BottomNavigationItem("Profile", Icons.Outlined.Person, Icons.Filled.Person, "profile")
    object Settings : BottomNavigationItem("Settings", Icons.Outlined.Settings, Icons.Filled.Settings ,"settings")
}
