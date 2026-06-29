package com.abhi.trackinfo.di

import com.abhi.trackinfo.data.local.dao.ProductDao
import com.abhi.trackinfo.data.local.dao.UserDao
import com.abhi.trackinfo.data.repository.AuthRepositoryImpl
import com.abhi.trackinfo.data.repository.FavouriteRepositoryImpl
import com.abhi.trackinfo.data.repository.HomeRepositoryImpl
import com.abhi.trackinfo.data.repository.ProductDetailsRepositoryImpl
import com.abhi.trackinfo.domain.repository.AuthRepository
import com.abhi.trackinfo.domain.repository.FavouritesRepository
import com.abhi.trackinfo.domain.repository.HomeRepository
import com.abhi.trackinfo.domain.repository.ProductDetailsRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideAuthRepository(
        userDao: UserDao
    ): AuthRepository = AuthRepositoryImpl(userDao)

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideHomeApiRepository(
        @Qualifiers.ApiClient sseClient: OkHttpClient,
        productDao: ProductDao,
        request: Request,
        gson: Gson
    ): HomeRepository = HomeRepositoryImpl(
        sseClient,
        productDao,
        request,
        gson
    )

    @Provides
    @Singleton
    fun provideProductDetailsRepository(
        productDao: ProductDao
    ): ProductDetailsRepository = ProductDetailsRepositoryImpl(
        productDao
    )

    @Provides
    @Singleton
    fun provideFavouriteRepository(
        productDao: ProductDao
    ): FavouritesRepository = FavouriteRepositoryImpl(
        productDao
    )

}
