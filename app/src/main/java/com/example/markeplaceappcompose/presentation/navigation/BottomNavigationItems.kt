package com.example.markeplaceappcompose.presentation.navigation


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

sealed class BottomNavigationItems(
    val title: String,
    val icon: ImageVector,
    val route: String
) {
    object Home : BottomNavigationItems("Home", Icons.Outlined.Home, "home")
    object Cart : BottomNavigationItems("Cart", Icons.Outlined.ShoppingCart, "cart")
    object Profile : BottomNavigationItems("Profile", Icons.Outlined.Person, "profile")

}
