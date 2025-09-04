package com.example.markeplaceappcompose.presentation.screens

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.markeplaceappcompose.data.local.dao.entity.ProductEntity
import com.example.markeplaceappcompose.presentation.viewmodel.product.ProductViewModel
import com.example.markeplaceappcompose.ui.productcard.ProductCard

@Composable
fun FavoritesScreen(viewModel: ProductViewModel) {

    val favorites by viewModel.favoriteProducts.collectAsState()

    Column(  modifier = Modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(modifier = Modifier.padding(15.dp), fontSize = 25.sp, text = "Favorites")
        LazyColumn {
            items(favorites) { product ->
                ProductCard(
                    product = product,
                    onFavoriteClick = { viewModel.onFavoriteClick(product.id) }
                )
            }
        }
    }
}
