package com.abhi.trackinfo.presentation.product_details

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abhi.trackinfo.domain.model.Product
import com.abhi.trackinfo.domain.usecase.util.Result

@Composable
fun ProductDetails(
    productId: String? = "",
    viewModel: ProductViewModel = hiltViewModel()
) {

    val id = productId?.toIntOrNull()
    val selectedState by viewModel.selectedProduct.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.fetchProductById(id?:1)
    }

    when (val state = selectedState) {
        null -> Text("Loading..")
        is Result.Success -> {
            ProductDetailsContent(state.data)
        }
        is Result.Error -> {}
    }
}


@Composable
private fun ProductDetailsContent(product: Product) {
    Column {
        Text("Product Details")
        Text("Name: ${product.name}")
        Text("Price: ${product.price}")
        Text("Stock: ${product.stock}")
        Text("Description: ${product.desc}")
        Text("Rating: ${product.rating}")
    }
}
