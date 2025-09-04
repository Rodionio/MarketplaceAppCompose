package com.example.markeplaceappcompose.presentation.viewmodel.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.markeplaceappcompose.data.local.dao.entity.ProductEntity
import com.example.markeplaceappcompose.domain.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<ProductListState>(ProductListState.Loading)
    val uiState: StateFlow<ProductListState> = _uiState.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _products = MutableStateFlow<List<ProductEntity>>(emptyList())
    val products: StateFlow<List<ProductEntity>> = _products.asStateFlow()

    init {
        observeProducts()
    }

    private fun observeProducts() {
        repository.getAllProduct()
            .catch { e ->
                _errorMessage.value = "Upload error: ${e.message}"
                _uiState.value = ProductListState.Error("Upload error: ${e.message}")
            }
            .onEach { productList ->
                _products.value = productList
                _uiState.value = if (productList.isEmpty()) ProductListState.Empty
                else ProductListState.Success(productList)
            }
            .launchIn(viewModelScope)


    }
    val favoriteProducts: StateFlow<List<ProductEntity>> =
        repository.getFavorites()
            .catch { _errorMessage.value = "Error loading favorites: ${it.message}" }
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun createProduct(
        name: String,
        price: String,
        imageUrl: String,
        description: String,
        onResult: (success: Boolean, error: String?) -> Unit
    ) {
        viewModelScope.launch {
            if (name.isBlank()) {
                onResult(false, "Name is required")
                return@launch
            }
            if (price.isBlank() || price.toIntOrNull() == null) {
                onResult(false, "Price is required")
                return@launch
            }
            if (imageUrl.isBlank()) {
                onResult(false, "Image is required")
                return@launch
            }
            if (description.isBlank()) {
                onResult(false, "Description is required")
                return@launch
            }

            try {
                repository.insert(
                    ProductEntity(
                        name = name.trim(),
                        description = description,
                        price = price,
                        imageUrl = imageUrl
                    )
                )
                onResult(true, null)
            } catch (e: Exception) {
                onResult(false, e.message ?: "Error saving product")
            }
        }
    }

    fun insertProduct(product: ProductEntity) {
        performOperation("add product") { repository.insert(product) }
    }

    fun insertProducts(products: List<ProductEntity>) {
        performOperation("add products") { repository.insertAll(products) }
    }

    fun updateProduct(product: ProductEntity) {
        performOperation("update") { repository.update(product) }
    }

    fun deleteProduct(product: ProductEntity) {
        performOperation("delete") { repository.delete(product) }
    }

    fun onFavoriteClick(productId: Int) {
        viewModelScope.launch {
            repository.toggleFavorite(productId)
        }
    }



    private fun performOperation(operationName: String, repositoryCall: suspend () -> Unit) {
        viewModelScope.launch {
            setLoading(true)
            try {
                repositoryCall()
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = "Error $operationName: ${e.message}"
            } finally {
                setLoading(false)
            }
        }
    }

    private fun setLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }

    fun clearError() {
        _errorMessage.value = null
    }

    fun setError(message: String) {
        _errorMessage.value = message
    }
}
