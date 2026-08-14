package com.abhi.amplify_store.presentation.utils.components.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.abhi.amplify_store.R
import com.abhi.amplify_store.presentation.utils.components.shared.MI_IconButton
import com.abhi.amplify_store.presentation.utils.navigation.Route
import com.abhi.amplify_store.ui.theme.White80

@Composable
fun MI_BottomBar(
    navController: NavController
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomAppBar(
        containerColor = White80,
        contentColor = Color.Yellow
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            bottomTabs.forEach { tab ->

                val isSelected = currentDestination?.hasRoute(tab.route::class) == true

                MI_IconButton(
                    moduleName = tab.id,
                    icon = tab.icon,
                    label = tab.id,
                    isSelected = isSelected
                ) {

                    navController.navigate(tab.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        }
    }
}

private val bottomTabs = listOf(
    BottomTab("Home", Route.Home,R.drawable.baseline_home_24),
    BottomTab("Favourite",Route.Favourites,R.drawable.outline_favorite_24),
    BottomTab("Profile",Route.Profile,R.drawable.outline_contacts_product_24),
    BottomTab("Settings",Route.Settings,R.drawable.baseline_settings_24)
)

private data class BottomTab(
    val id: String,
    val route: Route,
    val icon: Int
)

