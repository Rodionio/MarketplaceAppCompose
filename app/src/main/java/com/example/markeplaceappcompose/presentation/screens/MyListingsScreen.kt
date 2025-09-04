package com.example.markeplaceappcompose.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.markeplaceappcompose.presentation.viewmodel.product.ProductViewModel
import com.example.markeplaceappcompose.ui.myproductcard.MyListingsCard

@Composable
fun MyListingScreen(viewModel: ProductViewModel){

    val products by viewModel.products.collectAsState()


    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(products) { product ->
            MyListingsCard (
                product = product,
                onDelete = {  },
                onBuy = {  },
                onFavorite = { viewModel.onFavoriteClick(product.id) }
            )
        }
    }
}