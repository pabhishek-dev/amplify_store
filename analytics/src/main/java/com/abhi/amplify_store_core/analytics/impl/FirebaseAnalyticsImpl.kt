package com.abhi.amplify_store_core.analytics.impl

import android.content.Context
import android.os.Bundle
import com.abhi.amplify_store_core.analytics.api.AnalyticsEvent
import com.abhi.amplify_store_core.analytics.api.AnalyticsTracker
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class FirebaseAnalyticsImpl @Inject constructor(
    @ApplicationContext context: Context
): AnalyticsTracker {

    private val firebaseAnalytics = FirebaseAnalytics.getInstance(context)

    override fun logEvent(event: AnalyticsEvent) {
        val bundle = Bundle().apply {
            event.params.forEach { (key, value) ->
                when (value) {
                    is String -> putString(key, value)
                    is Int -> putInt(key, value)
                    is Long -> putLong(key, value)
                    is Double -> putDouble(key, value)
                    is Boolean -> putBoolean(key, value)
                }
            }
        }
        firebaseAnalytics.logEvent(event.eventName, bundle)
    }

    override fun setUserProperty(name: String, value: String) {
        firebaseAnalytics.setUserProperty(name, value)
    }

    override fun setUserId(userId: String?) {
        firebaseAnalytics.setUserId(userId)
    }
}