package com.abhi.amplify_store.presentation.utils.navigation

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
import com.abhi.amplify_store.presentation.authentication.login.LoginScreen
import com.abhi.amplify_store.presentation.authentication.register.RegisterScreen
import com.abhi.amplify_store.presentation.favourites.FavouriteScreen
import com.abhi.amplify_store.presentation.home.Home
import com.abhi.amplify_store.presentation.product_details.ProductDetails
import com.abhi.amplify_store.presentation.profile.ProfileScreen
import com.abhi.amplify_store.presentation.settings.SettingsScreen
import com.abhi.amplify_store.presentation.test.Test
import kotlinx.serialization.Serializable


sealed interface Route {
    @Serializable
    data object AuthGraph : Route
    @Serializable
    data object Login : Route

    @Serializable
    data object Register :Route
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

        }
    }
}

private fun NavGraphBuilder.mainGraph(
    snackBarHostState: SnackbarHostState,
    navController: NavController,
    modifier: Modifier
) {
    navigation<Route.MainGraph>(startDestination = Route.Login) {
        composable<Route.Home> {
            Home(
                modifier = modifier,
                navController = navController,
                snackBarHostState = snackBarHostState
                )
        }
        composable<Route.Profile>   { ProfileScreen(onNavClick = navController::popBackStack) }
        composable<Route.Settings>  { SettingsScreen(navController = navController) }
        composable<Route.Favourites>{ FavouriteScreen(navController = navController) }
        composable<Route.Login> {
            LoginScreen(
                snackBarHostState = snackBarHostState,
                onLoginSuccess = {
                    navController.navigate(Route.Home) {
                        popUpTo(Route.Login) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onRegisterClick = {
                    navController.navigate(Route.Register) {
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<Route.Register> {
            RegisterScreen(
                snackBarHostState = snackBarHostState,
                navigateToLogin = {
                    navController.navigate(Route.Login) {
                        popUpTo(Route.Register) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<Route.Test>      { Test() }
        composable<Route.ProductDetails> { entry ->
            val args = entry.toRoute<Route.ProductDetails>()
            ProductDetails(
                productId = args.productId,
                onBackClick = {navController.popBackStack()})
           }
    }
}
