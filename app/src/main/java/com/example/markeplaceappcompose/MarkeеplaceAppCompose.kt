package com.example.markeplaceappcompose

import android.app.Application
import com.example.markeplaceappcompose.data.repository.ProductRepositoryImpl
import com.example.markeplaceappcompose.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers

class MarkeeplaceAppCompose : Application() {

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