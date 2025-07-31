package com.example.markeplaceappcompose.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.markeplaceappcompose.ui.productcard.ProductList
import com.example.markeplaceappcompose.ui.searchbar.SearchBars

@Composable
fun SearchScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)){
        SearchBars()
    }
}