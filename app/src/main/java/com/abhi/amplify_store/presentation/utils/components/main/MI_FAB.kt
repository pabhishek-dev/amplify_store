package com.abhi.amplify_store.presentation.utils.components.main

import android.content.Context
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.abhi.amplify_store.R

@Composable
fun MI_FAB(
    context: Context,
    onFABClick:() -> Unit
) {
    FloatingActionButton(
        onClick = { onFABClick() },
        contentColor = Color.Yellow,
        containerColor = Color.Gray
    ) {
        Icon(
            painterResource(R.drawable.baseline_create_24),
            contentDescription = "FAB Icon"
        )
    }
}
