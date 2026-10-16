package com.abhi.amplify_store_core.analytics.api

sealed interface AnalyticsEvent{
    val eventName: String
    val params: Map<String, Any>
}

data class ProductViewedEvent(
    val productId: Int,
    val productName: String,
    val category: String = ""
): AnalyticsEvent{
    override val eventName: String = "product_viewed"
    override val params: Map<String, Any> = mapOf(
        "product_id" to productId,
        "product_name" to productName,
        "category" to category
    )
}

data class ScreenViewEvent(
    val screenName: String,
    val screenClass: String = "FragmentOrComposable"
) : AnalyticsEvent {
    override val eventName = "screen_view" // Standard Firebase event name
    override val params = mapOf(
        "firebase_screen" to screenName,
        "firebase_screen_class" to screenClass
    )
}
