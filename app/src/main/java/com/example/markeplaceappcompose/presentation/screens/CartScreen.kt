package com.example.markeplaceappcompose.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign


@Composable
fun CartScreen() {
    Text(
        modifier = Modifier.fillMaxSize(),
        text = "Cart",
        textAlign = TextAlign.Center
    )
}