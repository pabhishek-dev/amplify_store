package com.abhi.trackinfo.presentation.utils.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.abhi.trackinfo.presentation.authentication.Authentication
import com.abhi.trackinfo.presentation.favourites.FavouriteScreen
import com.abhi.trackinfo.presentation.home.Home
import com.abhi.trackinfo.presentation.product_details.ProductDetails
import com.abhi.trackinfo.presentation.profile.ProfileScreen
import com.abhi.trackinfo.presentation.settings.SettingsScreen
import com.abhi.trackinfo.presentation.test.Test
import kotlinx.serialization.Serializable


sealed interface Route {
    @Serializable
    data object AuthGraph : Route
    @Serializable
    data object Login : Route

    @Serializable
    data object MainGraph : Route
    @Serializable
    data object Home : Route
    @Serializable
    data object Profile : Route
    @Serializable
    data object Settings : Route
    @Serializable
    data object Favourites : Route
    @Serializable
    data object Test : Route
    @Serializable
    data class ProductDetails(val productId: String) : Route
}

@Composable
fun AppNavGraph(
    navController: NavHostController,
    snackBarHostState: SnackbarHostState,
    onFABClick: (() -> Unit) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Route.MainGraph,
        modifier = modifier,
        enterTransition = { fadeIn(animationSpec = tween(250)) },
        exitTransition = { fadeOut(animationSpec = tween(250)) },
        popEnterTransition = { fadeIn(animationSpec = tween(250)) },
        popExitTransition = { fadeOut(animationSpec = tween(250)) },
    ) {
        authGraph(
            snackBarHostState = snackBarHostState,
            onAuthSuccess = {
                navController.navigate(Route.MainGraph) {
                    popUpTo(Route.AuthGraph) { inclusive = true }
                    launchSingleTop = true
                }
            },
        )

        mainGraph(
            snackBarHostState = snackBarHostState,
            onFABClick = onFABClick,
            navController = navController,
            modifier = modifier
        )
    }
}

private fun NavGraphBuilder.authGraph(
    snackBarHostState: SnackbarHostState,
    onAuthSuccess: () -> Unit
) {
    navigation<Route.AuthGraph>(startDestination = Route.Login) {
        composable<Route.Login> {
            Authentication(
                snackBarHostState = snackBarHostState,
                onAuthSuccess = onAuthSuccess
            )
        }
    }
}

private fun NavGraphBuilder.mainGraph(
    snackBarHostState: SnackbarHostState,
    onFABClick: (() -> Unit) -> Unit,
    navController: NavController,
    modifier: Modifier
) {
    navigation<Route.MainGraph>(startDestination = Route.Home) {
        composable<Route.Home> {
            Home(navController = navController)
        }
        composable<Route.Profile>   { ProfileScreen(onNavClick = navController::popBackStack) }
        composable<Route.Settings>  { SettingsScreen(navController = navController) }
        composable<Route.Favourites>{ FavouriteScreen(navController = navController) }
        composable<Route.Test>      { Test() }
        composable<Route.ProductDetails> { entry ->
            val args = entry.toRoute<Route.ProductDetails>()
            ProductDetails(
                productId = args.productId,
                onBackClick = {navController.popBackStack()})
           }
    }
}
