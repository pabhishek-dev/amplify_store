package com.abhi.amplify_store.presentation.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.abhi.amplify_store.presentation.utils.components.main.MI_BottomBar
import com.abhi.amplify_store.presentation.utils.components.main.MI_FAB
import com.abhi.amplify_store.presentation.utils.components.main.MI_TopBar
import com.abhi.amplify_store.presentation.utils.navigation.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    modifier: Modifier,
    navController: NavController = rememberNavController(),
    snackBarHostState: SnackbarHostState,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var fabAction by remember { mutableStateOf<(() -> Unit)?>(null) }

    // Collapses the large top bar cleanly on scroll
    val scrollBehaviour = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    // UI State
    val homeUiState by viewModel.products.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier
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