package com.abhi.amplify_store.di

import javax.inject.Qualifier

object Qualifiers {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ApiClient

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class SSEClient
}
