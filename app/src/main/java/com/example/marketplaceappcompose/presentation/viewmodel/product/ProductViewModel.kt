package com.example.marketplaceappcompose.presentation.viewmodel.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketplaceappcompose.data.local.dao.entity.ProductEntity
import com.example.marketplaceappcompose.domain.repository.ProductRepository
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
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()


    val favoriteProducts: StateFlow<List<ProductEntity>> =
        repository.getFavorites()
            .catch { _errorMessage.value = "Error loading favorites: ${it.message}" }
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val cartProducts: StateFlow<List<ProductEntity>> =
        repository.getCartProducts()
            .catch { _errorMessage.value = "Error loading cart: ${it.message}" }
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())


    init {
        observeProducts()
    }


    fun createProduct(
        name: String,
        price: String,
        imagePath: String,
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
            if (imagePath.isBlank()) {
                onResult(false, "Image is required")
                return@launch
            }
            if (description.isBlank()) {
                onResult(false, "Description is required")
                return@launch
            }

            try {
                val newProduct = ProductEntity(
                    name = name.trim(),
                    description = description,
                    price = price,
                    imageUrl = imagePath,
                    isUserCreated = true
                )


                repository.insert(newProduct)


                onResult(true, null)
            } catch (e: Exception) {
                onResult(false, e.message ?: "Error saving product")
            }
        }
    }


    fun removeFromCart(product: ProductEntity) {
        performOperation("remove from cart") { repository.toggleCart(product.id) }
    }

    fun addToCart(product: ProductEntity) {
        performOperation("add to cart") { repository.toggleCart(product.id) }
    }


    fun deleteProduct(product: ProductEntity) {
        performOperation("delete") { repository.delete(product) }
    }


    fun onFavoriteClick(productId: Int) {
        viewModelScope.launch {
            repository.toggleFavorite(productId)
        }
    }

    fun setError(message: String) {
        _errorMessage.value = message
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

    private fun setLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }


}
