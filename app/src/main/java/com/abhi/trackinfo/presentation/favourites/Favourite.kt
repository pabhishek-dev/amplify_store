package com.abhi.trackinfo.presentation.favourites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.abhi.trackinfo.presentation.home.HomeUiState
import com.abhi.trackinfo.presentation.home.components.ProductViewCard
import com.abhi.trackinfo.presentation.utils.components.main.MI_BottomBar
import com.abhi.trackinfo.presentation.utils.components.shared.MI_TopHeader
import com.abhi.trackinfo.presentation.utils.navigation.Route

@Composable
fun Favourite(
    uiState: HomeUiState,
    onProductClick: (Int) -> Unit = {},
    onFavouriteClick: (Int) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .background(Color(0xFFD9CEC0))
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (uiState.products.isNotEmpty()) {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                modifier = Modifier.padding(8.dp)
            ) {
                items(uiState.products, key = { it.id }) { favouriteProduct ->
                    ProductViewCard(
                        product = favouriteProduct,
                        isFavourite = uiState.favouriteProductIds.contains(favouriteProduct.id),
                        click = { productId -> onProductClick(productId) },
                        onFav = { productId -> onFavouriteClick(productId)}
                    )
                }
            }
        }
    }
}

@Composable
fun FavouriteScreen(
    navController: NavController,
    viewModel: FavouriteViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets,
        topBar = {
            MI_TopHeader(
                title = "Favourite"
            )
        },
        bottomBar = {
            MI_BottomBar(
                navController = navController
            )
        }
    ){ innerPadding ->

        val uiState by viewModel.favourites.collectAsStateWithLifecycle()

        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Favourite(
                uiState = uiState,
                onProductClick = { productId ->
                    navController.navigate(Route.ProductDetails(productId.toString()))
                },
                onFavouriteClick = { productId ->
                    viewModel.toggleFavourite(productId)
                }
            )
        }
    }
}

@Preview
@Composable
fun FavouriteScreenPreview(){

}