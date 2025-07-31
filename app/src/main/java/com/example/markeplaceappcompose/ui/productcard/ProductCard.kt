package com.example.markeplaceappcompose.ui.productcard

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.markeplaceappcompose.R
import com.example.markeplaceappcompose.data.local.Product

@Composable
fun ProductList() {

    val productList = listOf(
        Product("RAM", "10000$", R.drawable.rammm),
        Product("LAMBA", "1000000$", R.drawable.lamba),
        Product("HAMMER", "1002300$", R.drawable.hummer),
        Product("COMARO", "100200$", R.drawable.comaro),
        Product("HAMMER", "1002300$", R.drawable.hummer),
        Product("HAMMER", "1002300$", R.drawable.hummer),
        Product("LAMBA", "1000000$", R.drawable.lamba),
        Product("COMARO", "100200$", R.drawable.comaro),
        Product("RAM", "10000$", R.drawable.rammm),

    )

    LazyVerticalGrid(
        columns = androidx.compose.foundation.lazy.grid.GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 70.dp),
    ) {
        itemsIndexed(productList) { index,product ->
            ProductCard(product.name,product.price,product.imageResId)

        }
    }

}

//@Preview(showBackground = true)
@Composable
fun ProductCard(name:String,price:String,image:Int) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopStart
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp)
                .clickable {  },
            shape = RoundedCornerShape(5.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,

            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                // .padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = image),
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
                    Text(text = name)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = price)
                }

            }


        }
    }
}
