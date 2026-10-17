package com.abhi.amplify_store.data.util.mapper

import com.abhi.amplify_store.domain.model.Product
import com.abhi.network.model.ProductResponse

fun ProductResponse.toDomainProduct(): Product {
    return Product(
        id,
        name,
        price,
        discountPrice,
        category,
        stock,
        imageUrl,
        desc,
        rating,
        reviewCount,
        isFeatured,
        isNewArrival,
        galleryImages,
        tags,
        specifications
    )
}

