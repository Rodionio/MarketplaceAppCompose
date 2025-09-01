package com.example.markeplaceappcompose.ui.navigation


import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.markeplaceappcompose.presentation.navigation.BottomNavigationItems


@Composable
fun BottomNavigation(
    navController: NavController
) {
    val selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    val listItems = listOf(
        BottomNavigationItems.Home,
        BottomNavigationItems.Search,
        BottomNavigationItems.Cart,
        BottomNavigationItems.Profile,
        BottomNavigationItems.Settings,
    )

    NavigationBar {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        listItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route)
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(text = item.title)
                }

            )


        }
    }
}