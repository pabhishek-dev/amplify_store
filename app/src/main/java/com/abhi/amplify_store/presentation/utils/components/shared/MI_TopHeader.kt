package com.abhi.amplify_store.presentation.utils.components.shared

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MI_TopHeader(
    title:String,
    backClick: () -> Unit = {},
    cartClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {Text(title)},
        navigationIcon = {
            IconButton(
                onClick = backClick,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Navigate to previous screen"
                )
            }
        },
        actions = {
            IconButton(
                onClick = cartClick
            ) {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = "Share"
                )
            }
        },
        modifier = Modifier,
        windowInsets = TopAppBarDefaults.windowInsets
    )
}

@Preview
@Composable
private fun MI_TopHeader_Display() {
    MI_TopHeader(
        title = "Default Title",
        cartClick = {},
        backClick = {}
    )
}