package com.abhi.trackinfo.data.model

import com.abhi.trackinfo.domain.model.Product


data class HomeResponse(
    val id: Int,
    val name: String,
    val price: Float,
    val stock: Int,
    val imageUrl: String,
    val desc: String,
    val rating: Float
)

fun HomeResponse.toDomainProduct(): Product {
    return Product(id, name, price, stock, imageUrl, desc, rating)
}

