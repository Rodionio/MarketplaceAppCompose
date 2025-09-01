package com.example.markeplaceappcompose.domain.repository

import com.example.markeplaceappcompose.data.local.dao.entity.ProductEntity
import com.example.markeplaceappcompose.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository{
     fun getAllProduct(): Flow<List<ProductEntity>>
    suspend fun insert(product: ProductEntity)
    suspend fun update(product: ProductEntity)
    suspend fun delete(product: ProductEntity)

    suspend fun insertAll(products: List<ProductEntity>)

 }