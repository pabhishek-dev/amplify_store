package com.abhi.trackinfo.domain.repository

import com.abhi.trackinfo.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface FavouritesRepository {

    // Add a product to favourites list

    suspend fun addFavourite(productId: Int)

    // Fetch favourites

    fun fetchFavourites(): Flow<List<Product>>

    fun observerFavouritesProductId(): Flow<Set<Int>>

    suspend fun removeFavourite(productId: Int)
}
