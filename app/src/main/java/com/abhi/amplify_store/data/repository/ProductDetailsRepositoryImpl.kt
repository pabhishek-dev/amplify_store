package com.abhi.amplify_store.data.repository

import com.abhi.amplify_store.data.local.dao.ProductDao
import com.abhi.amplify_store.data.local.entities.toDomainProduct
import com.abhi.amplify_store.domain.model.Product
import com.abhi.amplify_store.domain.repository.ProductDetailsRepository

class ProductDetailsRepositoryImpl(
    private val productDao: ProductDao,
) : ProductDetailsRepository {
    override suspend fun fetchProductDetails(productId: Int): Product {
        return productDao.fetchProduct(productId).toDomainProduct()
    }
}
