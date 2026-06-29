package com.abhi.trackinfo.di

import com.abhi.trackinfo.data.remote.HomeApiService
import com.abhi.trackinfo.data.remote.SSLApiService
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
}
