package com.abhi.trackinfo.presentation.utils.components.shared

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun MI_RatingBar(
    rating: Float,
    padding: Dp,
    size: Dp
) {
    Row(
        modifier = Modifier
            .padding(start = padding, top = padding)
    ) {
        repeat(5) { index ->
            val icon = when {
                index + 1 <= rating -> Icons.Default.Star        // full star
                index < rating -> Icons.Default.StarHalf        // half star
                else -> Icons.Default.StarBorder                // empty star
            }

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFFFFC107),
                modifier = Modifier.size(size)
            )
        }
    }
}
