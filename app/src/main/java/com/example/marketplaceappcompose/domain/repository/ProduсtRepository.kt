package com.example.marketplaceappcompose.domain.repository

import com.example.marketplaceappcompose.data.local.dao.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getAllProduct(): Flow<List<ProductEntity>>
    fun getFavorites(): Flow<List<ProductEntity>>
    fun getCartProducts(): Flow<List<ProductEntity>>
    suspend fun toggleFavorite(productId: Int)
    suspend fun toggleCart(productId: Int)
    suspend fun insert(product: ProductEntity)
    suspend fun update(product: ProductEntity)
    suspend fun delete(product: ProductEntity)
    suspend fun insertAll(products: List<ProductEntity>)

}