package com.example.marketplaceappcompose.presentation.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.marketplaceappcompose.presentation.viewmodel.product.ProductViewModel
import com.example.marketplaceappcompose.ui.productcard.ProductList
import com.example.marketplaceappcompose.ui.searchbar.SearchBar


@Composable
fun HomeScreen(
    productViewModel: ProductViewModel,
    navController: NavController
) {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        SearchBar(
            query = searchQuery,
            onQueryChange = { searchQuery = it },
            active = false,
            onActiveChange = {}
        )
        ProductList(
            productViewModel = productViewModel,
            navController = navController,
            searchQuery = searchQuery
        )
    }
}