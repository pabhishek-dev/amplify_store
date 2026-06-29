package com.abhi.trackinfo.presentation.home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@Composable
fun ProductNavigationTabs(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    ///val startDestination = ActivityNavigator.Destination.
}


enum class Destination(val route: String, val label: String) {
    
}
