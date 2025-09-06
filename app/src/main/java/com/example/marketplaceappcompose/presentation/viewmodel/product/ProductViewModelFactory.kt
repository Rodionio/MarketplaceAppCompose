package com.example.marketplaceappcompose.presentation.viewmodel.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marketplaceappcompose.domain.repository.ProductRepository


class ProductViewModelFactory(
    private val repository: ProductRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            return ProductViewModel(repository) as T

        }
        throw IllegalArgumentException("Unknown ViewModel class:${modelClass.name}")
    }

}