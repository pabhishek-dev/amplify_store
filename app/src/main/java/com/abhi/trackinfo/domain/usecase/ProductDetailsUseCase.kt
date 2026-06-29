package com.abhi.trackinfo.domain.usecase

import com.abhi.trackinfo.domain.model.Product
import com.abhi.trackinfo.domain.repository.ProductDetailsRepository

class ProductDetailsUseCase(
    private val productDetailsRepository: ProductDetailsRepository
) {

    suspend fun fetchProductById(productId: Int): Product {
       return productDetailsRepository.fetchProductDetails(productId)
    }
}
