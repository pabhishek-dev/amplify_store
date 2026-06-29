package com.abhi.trackinfo.domain.usecase

import com.abhi.trackinfo.domain.model.Product
import com.abhi.trackinfo.domain.repository.FavouritesRepository
import com.abhi.trackinfo.domain.usecase.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

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
