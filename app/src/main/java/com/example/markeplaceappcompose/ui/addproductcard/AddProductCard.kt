package com.example.markeplaceappcompose.ui.addproductcard


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun AddProductCard(
    name: String,
    price: String,
    description: String,
    imageUri: String?,
    onSelectImage: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clickable { onSelectImage() }
            ) {
                if (imageUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(imageUri),
                        contentDescription = name,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.LightGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Click to select image", color = Color.DarkGray)
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Name: $name", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Price: $price", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Description: $description", fontSize = 16.sp)
        }
    }
}
