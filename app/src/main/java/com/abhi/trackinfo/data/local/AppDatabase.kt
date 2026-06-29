package com.abhi.trackinfo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abhi.trackinfo.data.local.dao.ProductDao
import com.abhi.trackinfo.data.local.dao.UserDao
import com.abhi.trackinfo.data.local.entities.FavouriteEntity
import com.abhi.trackinfo.data.local.entities.ProductEntity
import com.abhi.trackinfo.data.local.entities.UserEntity

@Database(
    entities = [UserEntity::class, ProductEntity::class, FavouriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
}
