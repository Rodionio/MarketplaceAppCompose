package com.example.marketplaceappcompose.ui.productdetailcard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.marketplaceappcompose.data.local.dao.entity.ProductEntity

@Composable
fun ProductDetailCard(
    product: ProductEntity,
    isInCart: Boolean,
    onFavoriteClick: () -> Unit,
    onToggleCart: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(product.name, style = MaterialTheme.typography.headlineSmall)
        Text(
            "${product.price} $",
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF388E3C)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(product.description, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            IconButton(onClick = onFavoriteClick) {
                if (product.isFavorite) Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Favorite",
                    tint = Color.Red
                )
                else Icon(Icons.Outlined.FavoriteBorder, contentDescription = "Not Favorite")
            }

            Button(
                onClick = onToggleCart,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isInCart) Color.Gray else Color(
                        0xFF1976D2
                    )
                ),
                modifier = Modifier.weight(1f)
            ) {
                Text(if (isInCart) "Remove from Cart" else "Add to Cart")
            }
        }
    }
}

