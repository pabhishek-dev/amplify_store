package com.abhi.trackinfo.data.repository

import com.abhi.trackinfo.data.local.dao.ProductDao
import com.abhi.trackinfo.data.local.entities.toDomainProduct
import com.abhi.trackinfo.domain.model.Product
import com.abhi.trackinfo.domain.repository.ProductDetailsRepository

class ProductDetailsRepositoryImpl(
    private val productDao: ProductDao,
) : ProductDetailsRepository {
    override suspend fun fetchProductDetails(productId: Int): Product {
        return productDao.fetchProduct(productId).toDomainProduct()
    }
}
