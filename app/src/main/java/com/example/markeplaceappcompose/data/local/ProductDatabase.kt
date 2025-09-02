package com.example.markeplaceappcompose.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.markeplaceappcompose.data.local.dao.ProductDao
import com.example.markeplaceappcompose.data.local.dao.entity.ProductEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = true
)
abstract class ProductDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var INSTANCE: ProductDatabase? = null

        fun getDatabase(context: Context): ProductDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductDatabase::class.java,
                    "product.db"
                )
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            CoroutineScope(Dispatchers.IO).launch {
                                INSTANCE?.productDao()?.insertAll(PREPOPULATE_DATA)
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }

        val PREPOPULATE_DATA = listOf(
            ProductEntity(
                id = 1,
                name = "lamba",
                description = "car",
                price = "2000.5",
                imageUrl = "android.resource://com.example.markeplaceappcompose/drawable/lamba"
            ),
            ProductEntity(
                id = 2,
                name = "camaro",
                description = "car2",
                price = "2333",
                imageUrl = "android.resource://com.example.markeplaceappcompose/drawable/camaro"
            ),
            ProductEntity(
                id = 3,
                name = "rammm",
                description = "car3",
                price = "3442",
                imageUrl = "android.resource://com.example.markeplaceappcompose/drawable/rammm"
            )
        )
    }

}