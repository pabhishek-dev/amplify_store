package com.abhi.trackinfo.data.repository

import com.abhi.trackinfo.data.local.dao.ProductDao
import com.abhi.trackinfo.data.local.entities.FavouriteEntity
import com.abhi.trackinfo.data.local.entities.toDomainProduct
import com.abhi.trackinfo.domain.model.Product
import com.abhi.trackinfo.domain.repository.FavouritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavouriteRepositoryImpl(
    private val productDao: ProductDao
) : FavouritesRepository {

    override suspend fun addFavourite(productId: Int) {
        productDao.addFavourite(
            FavouriteEntity(
                productId = productId,
                timeStamp = System.currentTimeMillis()
            )
        )
    }

    override fun fetchFavourites(): Flow<List<Product>> {
        return productDao.getFavoriteProducts().map { entities ->
            entities.map { it.toDomainProduct() }
        }
    }

    override fun observerFavouritesProductId(): Flow<Set<Int>> {
        return productDao.observeFavouriteProductIds().map { it.toSet() }
    }

    override suspend fun removeFavourite(productId: Int) {
        productDao.removeFavourite(productId)
    }
}
