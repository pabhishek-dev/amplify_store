package com.abhi.trackinfo.presentation.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhi.trackinfo.domain.usecase.FavouriteUseCase
import com.abhi.trackinfo.domain.usecase.util.Result
import com.abhi.trackinfo.presentation.home.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val favouriteUseCase: FavouriteUseCase
) : ViewModel() {

    private var _internalLoadingState = MutableStateFlow(false)

    val favourites: StateFlow<HomeUiState> = combine(
        favouriteUseCase.fetchFavourites(),
        _internalLoadingState,
        favouriteUseCase.observeFavouritesProductId()) { result, isLoading, favouriteId ->
            when (result) {
                is Result.Success -> HomeUiState(
                    products = result.data,
                    favouriteProductIds = favouriteId,
                    isLoading = isLoading,
                    errorMessage = null
                )

                is Result.Error -> HomeUiState(
                    products = emptyList(),
                    isLoading = false,
                    errorMessage = result.message
                )
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState(isLoading = true)
        )

    fun toggleFavourite(productId: Int){
        viewModelScope.launch {
            val isFavourite = favourites.value.favouriteProductIds.contains(productId)
            if(isFavourite){
                favouriteUseCase.removeFavourites(productId)
            }else{
                favouriteUseCase.addFavourite(productId)
            }
        }
    }
}
