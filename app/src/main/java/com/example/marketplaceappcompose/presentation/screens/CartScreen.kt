package com.example.marketplaceappcompose.presentation.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.marketplaceappcompose.presentation.viewmodel.product.ProductViewModel
import com.example.marketplaceappcompose.ui.cartcard.CartCard

@Composable
fun CartScreen(
    viewModel: ProductViewModel,
    navController: NavController
) {
    val cartProducts by viewModel.cartProducts.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(15.dp),
            fontSize = 25.sp,
            text = "Cart"
        )

        if (cartProducts.isEmpty()) {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Your cart is empty",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
        } else {

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(cartProducts) { product ->
                    CartCard(
                        product = product,
                        onClick = { navController.navigate("product_detail/$it") },
                        onDelete = { viewModel.removeFromCart(it) },
                    )
                }
            }
        }
    }
}




