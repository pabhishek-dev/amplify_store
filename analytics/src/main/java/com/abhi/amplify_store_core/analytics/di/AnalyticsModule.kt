package com.abhi.amplify_store_core.analytics.di

import com.abhi.amplify_store_core.analytics.api.AnalyticsTracker
import com.abhi.amplify_store_core.analytics.impl.FirebaseAnalyticsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class AnalyticsModule {

    @Binds
    @Singleton
    abstract fun bindAnalyticsTracker(
        impl: FirebaseAnalyticsImpl
    ): AnalyticsTracker
}