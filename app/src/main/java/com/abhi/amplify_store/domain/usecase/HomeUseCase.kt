package com.abhi.amplify_store.domain.usecase

import com.abhi.amplify_store.domain.model.Product
import com.abhi.amplify_store.domain.repository.HomeRepository
import com.abhi.amplify_store.domain.usecase.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class HomeUseCase(
    private val homeRepository: HomeRepository
) {
    fun observeProducts(): Flow<Result<List<Product>>> {
        return homeRepository.observeProducts()
            .map<List<Product>, Result<List<Product>>> { Result.Success(it) }
            .catch { emit(Result.Error(it.message ?: "Stream failed")) }
    }

    suspend fun connectStream() {
        homeRepository.connectStream()
    }

}
