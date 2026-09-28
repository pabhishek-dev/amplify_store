package com.abhi.amplify_store.di

import com.abhi.amplify_store.data.local.dao.ProductDao
import com.abhi.amplify_store.data.remote.LoginApiService
import com.abhi.amplify_store.data.remote.RegisterApiService
import com.abhi.amplify_store.data.repository.FavouriteRepositoryImpl
import com.abhi.amplify_store.data.repository.HomeRepositoryImpl
import com.abhi.amplify_store.data.repository.LoginRepositoryImpl
import com.abhi.amplify_store.data.repository.ProductDetailsRepositoryImpl
import com.abhi.amplify_store.data.repository.RegisterRepositoryImpl
import com.abhi.amplify_store.domain.repository.FavouritesRepository
import com.abhi.amplify_store.domain.repository.HomeRepository
import com.abhi.amplify_store.domain.repository.LoginRepository
import com.abhi.amplify_store.domain.repository.ProductDetailsRepository
import com.abhi.amplify_store.domain.repository.RegisterRepository
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
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideHomeApiRepository(
        @Qualifiers.SSEClient sseClient: OkHttpClient,
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

    @Provides
    @Singleton
    fun provideRegisterRepository(
        registerApiService: RegisterApiService
    ): RegisterRepository = RegisterRepositoryImpl(
        registerApiService
    )

    @Provides
    @Singleton
    fun provideLoginRepository(
        loginApiService: LoginApiService
    ): LoginRepository = LoginRepositoryImpl(
        loginApiService
    )
}
