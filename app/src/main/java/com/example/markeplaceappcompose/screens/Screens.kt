package com.example.markeplaceappcompose.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun HomeScreen() {
    Text(
        modifier = Modifier.fillMaxSize(),
        text = "home",
        textAlign = TextAlign.Center
    )
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
    Text(
        modifier = Modifier.fillMaxSize(),
        text = "Search",
        textAlign = TextAlign.Center
    )
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