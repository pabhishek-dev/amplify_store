package com.abhi.amplify_store.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhi.amplify_store.domain.usecase.FavouriteUseCase
import com.abhi.amplify_store.domain.usecase.HomeUseCase
import com.abhi.amplify_store.domain.usecase.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase,
    private val favouriteUseCase: FavouriteUseCase
) : ViewModel() {

    // Here we have combined 2 flows to extract favourite id's and toggle them in list.
    val products: StateFlow<HomeUiState> = combine(
        homeUseCase.observeProducts(),
        favouriteUseCase.observeFavouritesProductId()
    ) { result, favouriteIds ->

        when (result) {
            is Result.Success -> {
                val uniqueProducts = result.data.distinctBy { it.id }
                HomeUiState(
                    products = uniqueProducts,
                    favouriteProductIds = favouriteIds,
                    isLoading = false
                )
            }

            is Result.Error -> HomeUiState(errorMessage = result.message, isLoading = false)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeUiState(isLoading = true)
    )
    private var streamJob: Job? = null

    init {
        connectStream()
    }

    fun connectStream() {
        if (streamJob?.isActive == true) return
        streamJob = viewModelScope.launch {
            try {
                homeUseCase.connectStream()
            } catch (e: Exception) {
                Timber.tag("HomeViewModel").e(e, "SSE failed: ${e.message}")
            }
        }
    }

    fun toggleFavourite(productId: Int) {
        viewModelScope.launch {
            val isFavourite = products.value.favouriteProductIds.contains(productId)
            if (isFavourite) {
                favouriteUseCase.removeFavourites(productId)
            } else {
                favouriteUseCase.addFavourite(productId)
            }
        }
    }
}
