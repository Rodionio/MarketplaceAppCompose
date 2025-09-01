package com.example.markeplaceappcompose.presentation.viewmodel.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.markeplaceappcompose.data.local.dao.entity.ProductEntity
import com.example.markeplaceappcompose.domain.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _uiState= MutableStateFlow<ProductListState>(ProductListState.Loading)
    val uiState: StateFlow<ProductListState> =_uiState.asStateFlow()
    private val _isLoading=MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> =_isLoading.asStateFlow()
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    private val _products = MutableStateFlow<List<ProductEntity>>(emptyList())
    val products: StateFlow<List<ProductEntity>> = _products.asStateFlow()


    init {
        observeProducts()
    }

    private fun observeProducts(){
        viewModelScope.launch {
            repository.getAllProduct()
                .catch {e->
                    _errorMessage.value="Upload error: ${e.message}"
                    _uiState.value= ProductListState.Error("Upload error: ${e.message}")
                }
                .onEach { productList ->
                    _products.value=productList
                    if(productList.isEmpty()){
                        _uiState.value= ProductListState.Empty
                    }else{
                        _uiState.value= ProductListState.Success(productList)
                    }

                }
                .launchIn(viewModelScope)
        }
    }

    fun insertProduct(product: ProductEntity) {
        performOperation(
            operationName = "add product",
            repositoryCall = { repository.insert(product) }
        )
    }
    fun insertProducts(products: List<ProductEntity>) {
        performOperation(
            operationName = "add products",
            repositoryCall = { repository.insertAll(products) }
        )
    }

    fun updateProduct(product: ProductEntity) {
        performOperation(
            operationName = "update",
            repositoryCall = { repository.update(product) }
        )
    }




    fun deleteProduct(product: ProductEntity) {
        performOperation(
            operationName = "delete",
            repositoryCall = { repository.delete(product) }
        )
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


    private fun setLoading(isLoading: Boolean){
        _isLoading.value=isLoading
    }

    fun clearError(){
        _errorMessage.value=null
    }

}