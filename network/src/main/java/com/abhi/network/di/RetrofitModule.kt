package com.abhi.network.di

import com.abhi.network.Constants.API_BASE_URL
import com.abhi.network.Constants.API_HOST
import com.abhi.network.Constants.API_STREAM_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideCertificatePinner(): CertificatePinner =
        CertificatePinner.Builder()
            .add(API_HOST, "sha256/8emdl/UmneUm0I4Y/vHzOTQzb9eJwG4voRHtMdNmBPk=")
            .build()

    @Provides
    @Singleton
    @Qualifiers.ApiClient
    fun provideOkHttpClient(
        certificatePinner: CertificatePinner
    ): OkHttpClient =
        OkHttpClient.Builder()
            .hostnameVerifier { _, _ -> true }
            .certificatePinner(certificatePinner)
            .build()

    @Provides
    @Singleton
    @Qualifiers.SSEClient
    fun provideSSEClient(    // Utilizing the same HttpClient as above instead of reusing
        @Qualifiers.ApiClient base: OkHttpClient
    ): OkHttpClient = base.newBuilder()
        .callTimeout(0, TimeUnit.SECONDS)
        .readTimeout(0, TimeUnit.SECONDS)
        .writeTimeout(0, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        @Qualifiers.ApiClient client: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideRequest(
    ): Request = Request.Builder()
        .url(API_STREAM_URL)
        .header("Accept", "text/event-stream")
        .build()

}
