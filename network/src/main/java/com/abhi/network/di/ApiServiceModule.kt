package com.abhi.network.di

import com.abhi.network.api.HomeApiService
import com.abhi.network.api.LoginApiService
import com.abhi.network.api.RegisterApiService
import com.abhi.network.api.SSLApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Provides
    @Singleton
    fun provideSSLService(
        retrofit: Retrofit
    ): SSLApiService = retrofit.create(SSLApiService::class.java)

    @Provides
    @Singleton
    fun provideHomeAPIService(
        retrofit: Retrofit
    ): HomeApiService = retrofit.create(HomeApiService::class.java)

    @Provides
    @Singleton
    fun provideLoginAPIService(
        retrofit: Retrofit
    ): LoginApiService = retrofit.create(LoginApiService::class.java)

    @Provides
    @Singleton
    fun provideRegisterApiService(
        retrofit: Retrofit
    ): RegisterApiService = retrofit.create(RegisterApiService::class.java)
}