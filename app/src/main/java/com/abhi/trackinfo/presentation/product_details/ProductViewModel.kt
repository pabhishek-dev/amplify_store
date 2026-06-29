package com.abhi.trackinfo.presentation.product_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhi.trackinfo.domain.model.Product
import com.abhi.trackinfo.domain.usecase.ProductDetailsUseCase
import com.abhi.trackinfo.domain.usecase.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productDetailsUseCase: ProductDetailsUseCase
): ViewModel() {

    private val _selectedProduct = MutableStateFlow<Result<Product>?>(null)
    val selectedProduct: StateFlow<Result<Product>?> = _selectedProduct.asStateFlow()

    fun fetchProductById(productId: Int) {
        viewModelScope.launch {
            try {
                val product = productDetailsUseCase.fetchProductById(productId)
                _selectedProduct.value = Result.Success(product)
            } catch (e: Exception) {
                _selectedProduct.value = Result.Error(e.message ?: "Unable to load product")
            }
        }
    }
}
