package com.abhi.amplify_store.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient

@EntryPoint
@InstallIn(SingletonComponent::class)
interface ImageLoadEntryPoint {
    @Qualifiers.ApiClient
    fun getOkHttpClient(): OkHttpClient
}
