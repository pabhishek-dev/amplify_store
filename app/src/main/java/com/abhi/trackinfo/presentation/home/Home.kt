package com.abhi.trackinfo.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.abhi.trackinfo.domain.model.Product
import com.abhi.trackinfo.domain.usecase.util.Result
import com.abhi.trackinfo.presentation.favourites.FavouriteViewModel
import com.abhi.trackinfo.presentation.home.components.ProductViewCard

@Composable
fun Home(
    modifier: Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    snackBarHostState: SnackbarHostState,
    onFABClick: () -> Unit,
    navController: NavController
) {

    val result by viewModel.products.collectAsStateWithLifecycle()

    DisposableEffect(Unit) {
        viewModel.connectStream()
        onDispose { onFABClick() }
    }

    HomeScreen(
        modifier = modifier
            .background(Color.White)
            .padding(30.dp),
        homeUiState = result,
        snackBarHostState = snackBarHostState,
        onFABClick = { onFABClick() },
        onProductClick = { getId ->
            navController.navigate("product_details/${getId}") {
               // popUpTo(Routes.AUTH) { inclusive = true }
            }
        },
        onFavouriteClick = { id ->
            viewModel.toggleFavourite(id)
        }
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeUiState: HomeUiState,
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    onFABClick: (() -> Unit) -> Unit = {},
    onProductClick: (String) -> Unit = {},
    onFavouriteClick: (Int) -> Unit = {}
) {

    Column(
        modifier = Modifier
            .background(Color(0xFFD9CEC0))
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (homeUiState.isLoading) {

        } else if (homeUiState.errorMessage != null) {

        } else {
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


@Preview(showBackground = true)
@Composable()
fun HomeScreenPreview() {

    val displayProducts = listOf(
        Product(1, "Laptop", 12.0f, 12, imageUrl = "", desc = "test", rating = 2.5f),
        Product(2, "Mobile", 17.0f, 1, imageUrl = "", desc = "test 2", rating = 3.5f),
    )

    HomeScreen(
        homeUiState = HomeUiState(
            products = displayProducts
        )
    )
}
