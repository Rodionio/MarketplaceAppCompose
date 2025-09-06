package com.example.marketplaceappcompose

import android.app.Application
import com.example.marketplaceappcompose.data.repository.ProductRepositoryImpl
import com.example.marketplaceappcompose.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers

class MarketplaceAppCompose : Application() {

    lateinit var productRepository: ProductRepository
        private set

    override fun onCreate() {
        super.onCreate()
        productRepository = ProductRepositoryImpl(
            context = this,
            backgroundDispatcher = Dispatchers.IO
        )
    }
}