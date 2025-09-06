package com.example.markeplaceappcompose.ui.cartcard

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.markeplaceappcompose.data.local.dao.entity.ProductEntity

@Composable
fun CartCard(
    product: ProductEntity,
    onClick: ((Int) -> Unit)? = null,
    onDelete: (ProductEntity) -> Unit,
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
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
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            Text(fontSize = 20.sp, text = product.name, modifier = Modifier.padding(8.dp))
            Text(fontSize = 18.sp, text = "${product.price}$", modifier = Modifier.padding(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(35.dp)
                        .clickable { onDelete(product) },
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "delete"
                )

                Text(
                    text = "Buy",
                    fontSize = 25.sp,
                    color = Color(0xFF1976D2),
                    modifier = Modifier
                        .clickable {
                            Toast
                                .makeText(
                                    context,
                                    "Oops, we'll add this in the future.",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        }
                        .padding(8.dp)
                )
            }
        }
    }
}
