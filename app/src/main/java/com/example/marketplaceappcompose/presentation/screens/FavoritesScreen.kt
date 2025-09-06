package com.example.marketplaceappcompose.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.marketplaceappcompose.presentation.viewmodel.product.ProductViewModel
import com.example.marketplaceappcompose.ui.productcard.ProductCard

@Composable
fun FavoritesScreen(viewModel: ProductViewModel, navController: NavController) {
    val favorites by viewModel.favoriteProducts.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(15.dp),
            fontSize = 25.sp,
            text = "Favorites"
        )

        if (favorites.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Your favorites list is empty",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(favorites) { product ->
                    ProductCard(
                        product = product,
                        onClick = { navController.navigate("product_detail/$it") },
                        onFavoriteClick = { viewModel.onFavoriteClick(product.id) }
                    )
                }
            }
        }
    }
}