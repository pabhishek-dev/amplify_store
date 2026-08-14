package com.abhi.amplify_store.presentation.product_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abhi.amplify_store.domain.usecase.util.Result
import com.abhi.amplify_store.presentation.utils.components.shared.MI_TopHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetails(
    productId: String? = "",
    onBackClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    viewModel: ProductViewModel = hiltViewModel()
) {
    val id = productId?.toIntOrNull()
    val selectedState by viewModel.selectedProduct.collectAsStateWithLifecycle()

    // Trigger data fetch when productId changes or enters composition
    LaunchedEffect(productId) {
        if (id != null) {
            viewModel.fetchProductById(id)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets,
        topBar = {
            MI_TopHeader(
                title = "Product Details",
                cartClick = onCartClick,
                backClick = onBackClick
            )
        }
    ) { innerPadding ->
        when (val state = selectedState) {
            null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is Result.Success -> {
                ProductDetailsContent(
                    product = state.data,
                    modifier = Modifier.padding(innerPadding)
                )
            }

            is Result.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.message,
                        color = Color.Red
                    )
                }
            }
        }
    }
}
