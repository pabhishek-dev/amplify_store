package com.abhi.trackinfo.domain.repository

import com.abhi.trackinfo.domain.model.Product

interface ProductDetailsRepository {

    // Used to fetch a single product based on ID

    suspend fun fetchProductDetails(productId: Int): Product
}
