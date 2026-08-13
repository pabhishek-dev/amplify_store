package com.abhi.amplify_store.domain.usecase

import com.abhi.amplify_store.domain.model.Product
import com.abhi.amplify_store.domain.repository.ProductDetailsRepository

class ProductDetailsUseCase(
    private val productDetailsRepository: ProductDetailsRepository
) {

    suspend fun fetchProductById(productId: Int): Product {
       return productDetailsRepository.fetchProductDetails(productId)
    }
}
