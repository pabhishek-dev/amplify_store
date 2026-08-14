package com.abhi.amplify_store.domain.model

data class Product(
    val id: Int,
    val name: String,
    val price: Float,
    val discountPrice: Float,
    val category: String,
    val stock: Int,
    val imageUrl: String,
    val desc: String,
    val rating: Float,
    val reviewCount: Int,
    val isFeatured: Boolean,
    val isNewArrival: Boolean,
    val galleryImages: List<String> = emptyList(),
    val tags: List<String> = emptyList(),
    val specifications: Map<String, String> = emptyMap()
)