package com.abhi.amplify_store.data.model


data class SSEEvent(
    val action: String,
    val products: List<ProductResponse>? = null, // Populated when action == "initial"
    val product: ProductResponse? = null,        // Populated when action == "insert" or "update"
    val productId: Int? = null             // Populated when action == "delete"
)
