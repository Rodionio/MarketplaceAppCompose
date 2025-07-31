package com.example.markeplaceappcompose.ui.searchbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.markeplaceappcompose.ui.theme.MyGray


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBars() {
    val query = remember {
        mutableStateOf("")
    }
    val active = remember {
        mutableStateOf(false)
    }
    SearchBar(

        query = query.value,
        onQueryChange = {
            query.value = it
        },
        onSearch = { text ->
            active.value = false
        },
        placeholder = {
            Text(text = "Search...")
        },
        colors = SearchBarDefaults.colors(
            containerColor = Color(MyGray.value)

        ),
        shape = SearchBarDefaults.inputFieldShape,
        active = active.value,
        onActiveChange = {
            active.value = it
        },
        modifier = Modifier
            .fillMaxWidth(),
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "Search")
        }

    ) {

    }

}

