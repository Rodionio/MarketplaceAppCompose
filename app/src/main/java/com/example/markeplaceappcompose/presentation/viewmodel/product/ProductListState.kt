package com.example.markeplaceappcompose.presentation.viewmodel.product

import com.example.markeplaceappcompose.data.local.dao.entity.ProductEntity

sealed class ProductListState {
    object Loading : ProductListState()
    object Empty : ProductListState()
    data class Success(val products: List<ProductEntity>) : ProductListState()
    data class Error(val message: String) : ProductListState()
}