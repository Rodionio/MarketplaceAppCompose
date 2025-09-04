package com.example.markeplaceappcompose.presentation.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.markeplaceappcompose.presentation.viewmodel.product.ProductViewModel
import com.example.markeplaceappcompose.ui.searchbar.SearchBars
import com.example.markeplaceappcompose.ui.productcard.ProductList


@Composable
fun HomeScreen(productViewModel: ProductViewModel, onAddProductClick: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)){
        SearchBars()
        ProductList(productViewModel = productViewModel)
    }



}