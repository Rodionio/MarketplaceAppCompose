package com.example.marketplaceappcompose.data.repository

import android.content.Context
import com.example.marketplaceappcompose.data.local.ProductDatabase
import com.example.marketplaceappcompose.data.local.dao.ProductDao
import com.example.marketplaceappcompose.data.local.dao.entity.ProductEntity
import com.example.marketplaceappcompose.domain.repository.ProductRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class ProductRepositoryImpl(
    context: Context,
    private val backgroundDispatcher: CoroutineDispatcher,
) : ProductRepository {
    private val productDao: ProductDao

    init {
        val database = ProductDatabase.getDatabase(context)
        productDao = database.productDao()
    }

    override fun getAllProduct(): Flow<List<ProductEntity>> {
        return productDao.getAllProduct()
    }

    override fun getFavorites(): Flow<List<ProductEntity>> {
        return productDao.getFavorites()
    }

    override fun getCartProducts(): Flow<List<ProductEntity>> {
        return productDao.getCartProducts()
    }


    override suspend fun insert(product: ProductEntity) {
        withContext(backgroundDispatcher) {
            productDao.insertProduct(product)
        }
    }

    override suspend fun update(product: ProductEntity) {
        withContext(backgroundDispatcher) {
            productDao.updateProduct(product)
        }
    }

    override suspend fun delete(product: ProductEntity) {
        withContext(backgroundDispatcher) {
            productDao.deleteProduct(product)
        }
    }

    override suspend fun insertAll(products: List<ProductEntity>) {
        withContext(backgroundDispatcher) {
            productDao.insertAll(products)
        }
    }

    override suspend fun toggleFavorite(productId: Int) {
        withContext(backgroundDispatcher) {
            val product = productDao.getAllProduct().first().find { it.id == productId }
            product?.let {
                val updatedProduct = it.copy(isFavorite = !it.isFavorite)
                productDao.updateProduct(updatedProduct)
            }
        }
    }

    override suspend fun toggleCart(productId: Int) {
        withContext(backgroundDispatcher) {
            val product = productDao.getAllProduct().first().find { it.id == productId }
            product?.let {
                val updatedProduct = it.copy(isInCart = !it.isInCart)
                productDao.updateProduct(updatedProduct)
            }
        }
    }
}
