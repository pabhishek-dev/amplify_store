package com.abhi.trackinfo.presentation.utils.components.main

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.abhi.trackinfo.presentation.utils.navigation.AppNavGraph
import com.abhi.trackinfo.presentation.utils.navigation.Route
import com.abhi.trackinfo.ui.theme.TrackInfoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main() {

    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    var fabAction by remember { mutableStateOf<(() -> Unit)?> (null) }

    val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination

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
                        context,
                        scrollBehaviour,
                        isVisible = currentRoute?.hasRoute(Route.Home::class) == true
                    )
                 },
            bottomBar = { MI_BottomBar(navController)},
            floatingActionButton = { MI_FAB(context) { fabAction?.invoke() }},
            snackbarHost = {
                SnackbarHost(snackBarHostState) { snackBarData ->
                    Snackbar(
                        snackbarData = snackBarData,
                        containerColor = Color.DarkGray,
                        contentColor = Color.White
                    )
                }
            }) { innerPadding ->
            AppNavGraph(
                navController = navController,
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                snackBarHostState = snackBarHostState,
                onFABClick = {action -> fabAction = action}
            )
        }
    }
}
