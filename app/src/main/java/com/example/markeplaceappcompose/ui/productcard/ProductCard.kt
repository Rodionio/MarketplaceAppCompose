package com.example.markeplaceappcompose.ui.productcard


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.markeplaceappcompose.data.local.dao.entity.ProductEntity
import com.example.markeplaceappcompose.presentation.viewmodel.product.ProductListState
import com.example.markeplaceappcompose.presentation.viewmodel.product.ProductViewModel

@Composable
fun ProductList(
    productViewModel: ProductViewModel,
    navController: NavController,
    searchQuery: String
) {
    val uiState by productViewModel.uiState.collectAsState()
    val products by productViewModel.products.collectAsState()


    val filteredProducts = if (searchQuery.isBlank()) {
        products
    } else {
        products.filter { it.name.contains(searchQuery, ignoreCase = true) }
    }

    when (uiState) {
        is ProductListState.Loading -> CenterText("Loading...")
        is ProductListState.Empty -> {
            if (filteredProducts.isEmpty()) {
                CenterText("No products found")
            } else {
                ProductGrid(filteredProducts, navController, productViewModel)
            }
        }

        is ProductListState.Error -> CenterText((uiState as ProductListState.Error).message)
        is ProductListState.Success -> {
            if (filteredProducts.isEmpty()) {
                CenterText("No products found")
            } else {
                ProductGrid(filteredProducts, navController, productViewModel)
            }
        }
    }
}

@Composable
private fun ProductGrid(
    products: List<ProductEntity>,
    navController: NavController,
    productViewModel: ProductViewModel
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
    ) {
        items(products) { product ->
            ProductCard(
                product = product,
                onClick = { navController.navigate("product_detail/${product.id}") },
                onFavoriteClick = { productViewModel.onFavoriteClick(it) }
            )
        }
    }
}

@Composable
fun ProductCard(
    product: ProductEntity,
    onClick: ((Int) -> Unit)? = null,
    onFavoriteClick: (productId: Int) -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopStart
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp)
                .clickable { onClick?.invoke(product.id) },
            shape = RoundedCornerShape(5.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = "image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = product.name, fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "${product.price} $", fontSize = 18.sp)
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.End
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(20.dp)
                                .clickable { onFavoriteClick(product.id) },
                            imageVector = if (product.isFavorite) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "favorite",
                            tint = if (product.isFavorite) Color.Red else Color.Gray
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CenterText(text: String, fontSize: TextUnit = 25.sp) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = text, fontSize = fontSize)
    }
}
