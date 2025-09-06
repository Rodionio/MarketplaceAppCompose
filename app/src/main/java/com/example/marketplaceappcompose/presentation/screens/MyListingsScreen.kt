package com.example.marketplaceappcompose.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.marketplaceappcompose.ui.mylistingscard.MyListingsCard

@Composable
fun MyListingScreen(viewModel: ProductViewModel, navController: NavController) {

    val products by viewModel.products.collectAsState()
    val userProducts = products.filter { it.isUserCreated }

    if (userProducts.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Your listing is empty",
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
    } else

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(userProducts) { product ->
                MyListingsCard(
                    product = product,
                    onDelete = { viewModel.deleteProduct(product) },
                    onClick = { navController.navigate("product_detail/$it") },
                    onFavorite = { viewModel.onFavoriteClick(product.id) }
                )
            }
        }
}