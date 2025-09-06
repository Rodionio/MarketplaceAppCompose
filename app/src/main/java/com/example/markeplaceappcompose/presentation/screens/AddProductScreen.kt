package com.example.markeplaceappcompose.presentation.screens


import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.markeplaceappcompose.presentation.viewmodel.product.ProductViewModel
import com.example.markeplaceappcompose.ui.addproductcard.AddProductCard
import java.io.File

@Composable
fun AddProductScreen(
    viewModel: ProductViewModel,
    onProductAdded: () -> Unit
) {
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var imagePath by remember { mutableStateOf<String?>(null) }

    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        imagePath = uri?.let { savePickedImage(context, it) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        AddProductCard(
            name = name,
            price = price,
            description = description,
            imagePath = imagePath,
            onSelectImage = { launcher.launch("image/*") }
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Product Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Price") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 4
        )

        if (errorMessage != null) {
            Text(
                text = errorMessage ?: "",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Button(
            onClick = {
                if (imagePath == null) {
                    viewModel.setError("Image is required")
                    return@Button
                }

                viewModel.createProduct(
                    name,
                    price,
                    imagePath!!,
                    description
                ) { success, _ ->
                    if (success) onProductAdded()
                }
            },
            enabled = !isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(if (isLoading) "Adding..." else "Add Product", fontSize = 16.sp)
        }
    }
}

fun savePickedImage(context: Context, uri: Uri): String {
    val dir = File(context.filesDir, "images").apply { mkdirs() }
    val ext = context.contentResolver.getType(uri)?.let {
        MimeTypeMap.getSingleton().getExtensionFromMimeType(it)
    } ?: "jpg"
    val file = File(dir, "${System.currentTimeMillis()}.$ext")
    context.contentResolver.openInputStream(uri)?.use { input ->
        file.outputStream().use { output ->
            input.copyTo(output)
        }
    }
    return file.absolutePath
}

