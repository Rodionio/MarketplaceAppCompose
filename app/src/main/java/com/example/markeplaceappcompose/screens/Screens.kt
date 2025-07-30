package com.example.markeplaceappcompose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.markeplaceappcompose.R
import com.example.markeplaceappcompose.searchbar.SearchBars
import com.example.markeplaceappcompose.ui.ProductCard
import com.example.markeplaceappcompose.ui.ProductList

@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)){
        SearchBars()
        ProductList()
    }



    }



@Composable
fun ProfileScreen() {
    Text(
        modifier = Modifier.fillMaxSize(),
        text = "profile",
        textAlign = TextAlign.Center
    )
}

@Composable
fun SearchScreen() {
    SearchBars()
}

@Composable
fun CartScreen() {
    Text(
        modifier = Modifier.fillMaxSize(),
        text = "Cart",
        textAlign = TextAlign.Center
    )
}

@Composable
fun SettingsScreen() {
    Text(
        modifier = Modifier.fillMaxSize(),
        text = "Settings",
        textAlign = TextAlign.Center
    )

}