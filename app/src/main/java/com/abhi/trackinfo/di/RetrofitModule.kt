package com.abhi.trackinfo.di

import com.abhi.trackinfo.data.remote.SSLApiService
import com.abhi.trackinfo.data.repository.SSLPinningRepositoryImpl
import com.abhi.trackinfo.data.util.DatabaseConstants.API_PATH
import com.abhi.trackinfo.domain.repository.SSLPinningRepository
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
            .add(API_PATH, "sha256/hmXSu/8Q0vtd8hgxvtTphbv31whhMrTuIpliaHHfhzw=")
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
        .readTimeout(0, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        @Qualifiers.ApiClient client: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://${API_PATH}:4443/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideRequest(
    ): Request = Request.Builder()
        .url("https://${API_PATH}:4443/stream")
        .build()


    @Provides
    @Singleton
    fun provideSSLPinning(
        sslApiService: SSLApiService
    ): SSLPinningRepository = SSLPinningRepositoryImpl(sslApiService)

}
