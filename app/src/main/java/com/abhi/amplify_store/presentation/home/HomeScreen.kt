package com.abhi.amplify_store.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abhi.amplify_store.presentation.home.components.ProductViewCard

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeUiState: HomeUiState,
    onProductClick: (String) -> Unit = {},
    onFavouriteClick: (Int) -> Unit = {}
) {
    Column(
        modifier = modifier
            .background(Color(0xFFD9CEC0))
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            homeUiState.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.padding(32.dp))
            }
            homeUiState.errorMessage != null -> {
                Text(
                    text = homeUiState.errorMessage,
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp)
                )
            }
            else -> {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    modifier = Modifier.padding(8.dp)
                ) {
                    items(homeUiState.products, key = { it.id }) { product ->
                        ProductViewCard(
                            product = product,
                            isFavourite = homeUiState.favouriteProductIds.contains(product.id),
                            click = { getId -> onProductClick(getId.toString()) },
                            onFav = { id -> onFavouriteClick(id) }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {

}