package com.abhi.amplify_store.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.abhi.amplify_store.data.local.converters.Converters
import com.abhi.amplify_store.data.local.dao.ProductDao
import com.abhi.amplify_store.data.local.dao.UserDao
import com.abhi.amplify_store.data.local.entities.FavouriteEntity
import com.abhi.amplify_store.data.local.entities.ProductEntity
import com.abhi.amplify_store.data.local.entities.UserEntity

@Database(
    entities = [UserEntity::class, ProductEntity::class, FavouriteEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
}
