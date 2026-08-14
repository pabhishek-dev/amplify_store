package com.abhi.amplify_store.data.util.mapper

import com.abhi.amplify_store.data.local.entities.ProductEntity
import com.abhi.amplify_store.domain.model.Product

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        name = name,
        price = price,
        discountPrice = discountPrice,
        category = category,
        rating = rating,
        stock = stock,
        reviewCount = reviewCount,
        isFeatured = isFeatured,
        isNewArrival = isNewArrival,
        imageUrl = imageUrl,
        galleryImages = galleryImages,
        desc = desc,
        tags = tags,
        specifications = specifications
    )
}
