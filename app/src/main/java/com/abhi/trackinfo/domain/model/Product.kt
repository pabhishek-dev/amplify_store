package com.abhi.trackinfo.domain.model

data class Product(
    val id: Int,
    val name: String,
    val price: Float,
    val stock: Int,
    val imageUrl: String,
    val desc: String,
    val rating: Float
)
