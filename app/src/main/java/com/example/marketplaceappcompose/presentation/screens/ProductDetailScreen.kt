package com.example.marketplaceappcompose.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.marketplaceappcompose.presentation.viewmodel.product.ProductViewModel
import com.example.marketplaceappcompose.ui.productdetailcard.ProductDetailCard

@Composable
fun ProductDetailScreen(
    viewModel: ProductViewModel,
    productId: Int
) {
    val products by viewModel.products.collectAsState()
    val cartProducts by viewModel.cartProducts.collectAsState()

    val product = products.find { it.id == productId } ?: return Box(
        Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) { Text("Product not found") }

    val isInCart = cartProducts.contains(product)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        ProductImageSection(imageUrl = product.imageUrl, name = product.name)

        Spacer(Modifier.height(16.dp))

        ProductDetailCard(
            product = product,
            isInCart = isInCart,
            onFavoriteClick = { viewModel.onFavoriteClick(product.id) },
            onToggleCart = {
                if (isInCart) viewModel.removeFromCart(product)
                else viewModel.addToCart(product)
            }
        )
    }
}


@Composable
fun ProductImageSection(imageUrl: String, name: String) {
    AsyncImage(
        model = imageUrl,
        contentDescription = name,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    )
}
