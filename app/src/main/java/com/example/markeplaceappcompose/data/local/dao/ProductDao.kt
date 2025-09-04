package com.example.markeplaceappcompose.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.markeplaceappcompose.data.local.dao.entity.ProductEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface ProductDao {

    @Query("SELECT * FROM products ORDER BY name ASC")
     fun getAllProduct(): Flow<List<ProductEntity>>
    @Query("SELECT * FROM products WHERE isFavorite = 1 ORDER BY name ASC")
    fun getFavorites(): Flow<List<ProductEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity)
    @Update
    suspend fun updateProduct(product: ProductEntity)
    @Delete
    suspend fun deleteProduct(product: ProductEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(product: List<ProductEntity>)

}