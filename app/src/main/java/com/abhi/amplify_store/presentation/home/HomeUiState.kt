package com.abhi.amplify_store.presentation.home

import com.abhi.amplify_store.domain.model.Product

data class HomeUiState(
    val products: List<Product> = emptyList(),
    val favouriteProductIds: Set<Int> = emptySet(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
