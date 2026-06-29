package com.abhi.trackinfo.presentation.home.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color

@Composable
fun FavouriteIcon(
    isFavourite: Boolean,
    onToggle: () -> Unit
){
    val scale by animateFloatAsState(
        targetValue = if (isFavourite) 1.2f else 1f,
        label = "scale"
    )

    val color by animateColorAsState(
        targetValue = if (isFavourite) Color.Red else Color.Gray,
        label = "color"
    )

    IconButton(onClick = onToggle) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "Favourite",
            tint = color,
            modifier = Modifier.scale(scale)
        )
    }
}
