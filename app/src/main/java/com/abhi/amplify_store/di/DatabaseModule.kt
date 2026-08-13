package com.abhi.amplify_store.di

import android.app.Application
import androidx.room.Room
import com.abhi.amplify_store.data.local.AppDatabase
import com.abhi.amplify_store.data.local.dao.ProductDao
import com.abhi.amplify_store.data.local.dao.UserDao
import com.abhi.amplify_store.data.util.DatabaseConstants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase =
        Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            DB_NAME
        ).build()

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

    @Provides
    fun provideProductDao(db: AppDatabase): ProductDao = db.productDao()
}
