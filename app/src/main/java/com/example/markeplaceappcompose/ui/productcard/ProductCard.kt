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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.markeplaceappcompose.MarkeeplaceAppCompose
import com.example.markeplaceappcompose.R
import com.example.markeplaceappcompose.domain.model.Product
import com.example.markeplaceappcompose.presentation.viewmodel.product.ProductListState
import com.example.markeplaceappcompose.presentation.viewmodel.product.ProductViewModel
import com.example.markeplaceappcompose.presentation.viewmodel.product.ProductViewModelFactory

@Composable
fun ProductList() {

    val app = LocalContext.current.applicationContext as MarkeeplaceAppCompose
    val vm: ProductViewModel = viewModel(factory = ProductViewModelFactory(app.productRepository))

    val uiState by vm.uiState.collectAsState()
    val products by vm.products.collectAsState()

    when(uiState){
        is ProductListState.Loading -> CenterText("Loading...")
        is ProductListState.Empty -> CenterText("Empty for now")
        is ProductListState.Error -> CenterText((uiState as ProductListState.Error).message)
        is ProductListState.Success ->{
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 70.dp),
            ) {
                items(products) {  product ->
                    ProductCard(
                        name = product.name,
                        price = "${product.price} $",
                        imageUrl = product.imageUrl

                    )


                }
            }
        }
    }


}

@Composable
private fun CenterText(text: String) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text)
    }
}

//@Preview(showBackground = true)
@Composable
fun ProductCard(name: String, price: String, imageUrl: String) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopStart
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp)
                .clickable { },
            shape = RoundedCornerShape(5.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,

                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    model = imageUrl,
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
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .size(20.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(20.dp)
                                .clickable { },
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "favorite"
                        )

                    }
                }

            }


        }
    }
}
