package com.abhi.amplify_store_core.analytics.api

interface AnalyticsTracker {
    fun logEvent(event: AnalyticsEvent)
    fun setUserProperty(name: String, value: String)
    fun setUserId(userId: String?)
}