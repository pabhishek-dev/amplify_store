package com.abhi.network.di

import javax.inject.Qualifier

object Qualifiers {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ApiClient

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class SSEClient
}

@Qualifier
annotation class IoDispatcher

@Qualifier
annotation class MainDispatcher

@Qualifier
annotation class DefaultDispatcher