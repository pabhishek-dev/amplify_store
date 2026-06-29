package com.abhi.trackinfo.di

import javax.inject.Qualifier

object Qualifiers {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ApiClient

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class SSEClient
}
