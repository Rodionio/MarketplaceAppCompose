package com.example.markeplaceappcompose.presentation.screens



import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.markeplaceappcompose.presentation.viewmodel.product.ProductViewModel
import com.example.markeplaceappcompose.ui.addproductcard.AddProductCard

@Composable
fun AddProductScreen(
    viewModel: ProductViewModel,
    onProductAdded: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<String?>(null) }

    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        imageUri = uri?.toString()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        AddProductCard(
            name = name,
            price = price,
            description = description,
            imageUri = imageUri,
            onSelectImage = { launcher.launch("image/*") }
        )

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )

        TextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Price") }
        )

        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") }
        )

        if (errorMessage != null) {
            Text(text = errorMessage ?: "", color = MaterialTheme.colorScheme.error)
        }

        Button(
            onClick = {
                if (imageUri == null) {
                    viewModel.setError("Image is required")
                    return@Button
                }

                viewModel.createProduct(
                    name,
                    price,
                    imageUri!!,
                    description
                ) { success, _ ->
                    if (success) onProductAdded()
                }
            },
            enabled = !isLoading
        ) {
            Text(if (isLoading) "Adding..." else "Add Product")
        }
    }
}

