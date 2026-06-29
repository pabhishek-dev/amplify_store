package com.abhi.trackinfo.presentation.utils.components.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.abhi.trackinfo.R
import com.abhi.trackinfo.presentation.utils.components.shared.MI_IconButton
import com.abhi.trackinfo.presentation.utils.navigation.Route
import com.abhi.trackinfo.ui.theme.White80

@Composable
fun MI_BottomBar(
    navController: NavController
) {
    var selected by remember { mutableStateOf("Home") }

    BottomAppBar(
        containerColor = White80, contentColor = Color.Yellow
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            bottomTabs.forEach { tab ->
                MI_IconButton(
                    moduleName = tab.id,
                    icon = tab.icon,
                    label = tab.id,
                    isSelected = selected == tab.id
                ) {
                    selected = tab.id
                    tab.route.let { navController.navigate(it) }
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

