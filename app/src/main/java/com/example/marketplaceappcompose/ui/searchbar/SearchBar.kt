package com.example.marketplaceappcompose.ui.searchbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.marketplaceappcompose.ui.theme.MyGray


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    active: Boolean,
    onActiveChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = { onActiveChange(false) },
        active = active,
        onActiveChange = onActiveChange,
        modifier = modifier.fillMaxWidth(),
        placeholder = { Text("Search on Marketplace") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
        colors = androidx.compose.material3.SearchBarDefaults.colors(
            containerColor = Color(MyGray.value)
        )
    ) {

    }
}