package com.ap.cartly.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ap.cartly.data.local.dao.ProductDao
import com.ap.cartly.data.local.dao.UserDao
import com.ap.cartly.data.local.entity.ProductEntity
import com.ap.cartly.data.local.entity.UserEntity

@Database(
    entities = [
        ProductEntity::class,
        UserEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class CartlyDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: CartlyDatabase? = null

        fun getDatabase(context: Context): CartlyDatabase {
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CartlyDatabase::class.java,
                    "cartly_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}

