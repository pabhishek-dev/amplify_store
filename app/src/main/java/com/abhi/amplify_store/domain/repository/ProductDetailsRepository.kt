package com.abhi.amplify_store.domain.repository

import com.abhi.amplify_store.domain.model.Product

interface ProductDetailsRepository {

    // Used to fetch a single product based on ID

    suspend fun fetchProductDetails(productId: Int): Product
}
