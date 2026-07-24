package com.abhi.trackinfo.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.abhi.trackinfo.domain.model.Product
import com.abhi.trackinfo.presentation.home.components.ProductViewCard
import com.abhi.trackinfo.presentation.utils.components.main.MI_BottomBar
import com.abhi.trackinfo.presentation.utils.components.main.MI_FAB
import com.abhi.trackinfo.presentation.utils.components.main.MI_TopBar
import com.abhi.trackinfo.presentation.utils.navigation.Route
import com.abhi.trackinfo.ui.theme.TrackInfoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navController: NavController = rememberNavController(),
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val snackBarHostState = remember { SnackbarHostState() }
    var fabAction by remember { mutableStateOf<(() -> Unit)?>(null) }

    // Collapses the large top bar cleanly on scroll
    val scrollBehaviour = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    // UI State
    val homeUiState by viewModel.products.collectAsStateWithLifecycle()

    DisposableEffect(Unit) {
        viewModel.connectStream()
        onDispose { /* Cleanup stream if needed */ }
    }

    TrackInfoTheme(
        darkTheme = isSystemInDarkTheme(),
        dynamicColor = false
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehaviour.nestedScrollConnection),
            topBar = {
                MI_TopBar(
                    context = context,
                    scrollBehaviour = scrollBehaviour
                )
            },
            bottomBar = {
                MI_BottomBar(navController = navController)
            },
            floatingActionButton = {
                MI_FAB(context = context) { fabAction?.invoke() }
            },
            snackbarHost = {
                SnackbarHost(hostState = snackBarHostState) { snackBarData ->
                    Snackbar(
                        snackbarData = snackBarData,
                        containerColor = Color.DarkGray,
                        contentColor = Color.White
                    )
                }
            }
        ) { innerPadding ->
            HomeScreen(
                modifier = Modifier.padding(innerPadding),
                homeUiState = homeUiState,
                onProductClick = { productId ->
                    navController.navigate(Route.ProductDetails(productId = productId))
                },
                onFavouriteClick = { id ->
                    viewModel.toggleFavourite(id)
                }
            )
        }
    }
}

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
    val displayProducts = listOf(
        Product(1, "Laptop", 12.0f, 12, imageUrl = "", desc = "test", rating = 2.5f),
        Product(2, "Mobile", 17.0f, 1, imageUrl = "", desc = "test 2", rating = 3.5f),
    )

    HomeScreen(
        homeUiState = HomeUiState(
            products = displayProducts,
            favouriteProductIds = setOf(1)
        )
    )
}