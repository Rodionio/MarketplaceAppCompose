package com.example.markeplaceappcompose.ui.mylistingscard


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.markeplaceappcompose.data.local.dao.entity.ProductEntity

@Composable
fun MyListingsCard(
    product: ProductEntity,
    onClick: ((Int) -> Unit)? = null,
    onDelete: () -> Unit,
    onFavorite: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick?.invoke(product.id) },
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(fontSize = 20.sp, text = product.name, modifier = Modifier.padding(start = 8.dp))
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                fontSize = 18.sp,
                text = "${product.price}$",
                modifier = Modifier.padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        modifier = Modifier
                            .size(28.dp)
                            .clickable { onDelete() },
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "delete",
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Icon(
                        modifier = Modifier
                            .size(28.dp)
                            .clickable { onFavorite() },
                        imageVector = if (product.isFavorite) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "favorite",
                        tint = if (product.isFavorite) Color.Red else Color.Gray
                    )
                }

            }
        }
    }
}
