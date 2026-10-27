package com.abhi.network.model

data class ProductResponse(
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
    val galleryImages: List<String>,
    val tags: List<String>,
    val specifications: Map<String, String>,
)

