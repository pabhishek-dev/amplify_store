package com.abhi.trackinfo.data.util.mapper

import com.abhi.trackinfo.data.local.entities.ProductEntity
import com.abhi.trackinfo.domain.model.Product

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        name = name,
        price = price,
        stock = stock,
        imageUrl = imageUrl,
        desc = desc,
        rating = rating
    )
}
