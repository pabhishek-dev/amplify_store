package com.abhi.amplify_store.domain.usecase

import com.abhi.amplify_store.domain.model.Product
import com.abhi.amplify_store.domain.repository.FavouritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import com.abhi.common.Result

class FavouriteUseCase(
    private val favouriteRepository: FavouritesRepository
) {

    suspend fun addFavourite(productId: Int) {
        favouriteRepository.addFavourite(productId)
    }

    fun fetchFavourites(): Flow<Result<List<Product>>> {
        return favouriteRepository.fetchFavourites()
            .map<List<Product>, Result<List<Product>>> {
                Result.Success(
                    it
                )
            }
            .catch { emit(Result.Error(it.message ?: "Stream failed")) }
    }

    suspend fun removeFavourites(productId: Int){
        favouriteRepository.removeFavourite(productId)
    }

    fun observeFavouritesProductId(): Flow<Set<Int>>{
        return favouriteRepository.observerFavouritesProductId()
    }

}
