package com.abhi.trackinfo.presentation.home

import com.abhi.trackinfo.domain.model.Product

data class HomeUiState(
    val products: List<Product> = emptyList(),
    val favouriteProductIds: Set<Int> = emptySet(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
