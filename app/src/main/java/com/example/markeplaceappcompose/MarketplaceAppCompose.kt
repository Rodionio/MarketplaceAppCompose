package com.example.markeplaceappcompose

import android.app.Application
import com.example.markeplaceappcompose.data.local.ProductDatabase
import com.example.markeplaceappcompose.data.local.dao.ProductDao
import com.example.markeplaceappcompose.data.repository.ProductRepositoryImpl
import com.example.markeplaceappcompose.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlin.getValue

class MarketplaceAppCompose: Application() {

  lateinit var productRepository: ProductRepository

  override fun onCreate() {
    super.onCreate()
    productRepository = ProductRepositoryImpl(context = this, backgroundDispatcher = Dispatchers.IO)
  }

}
