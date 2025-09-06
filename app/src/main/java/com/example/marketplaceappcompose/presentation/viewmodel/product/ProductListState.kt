package com.example.marketplaceappcompose.presentation.viewmodel.product

import com.example.marketplaceappcompose.data.local.dao.entity.ProductEntity

sealed class ProductListState {
    object Loading : ProductListState()
    object Empty : ProductListState()
    data class Success(val products: List<ProductEntity>) : ProductListState()
    data class Error(val message: String) : ProductListState()
}