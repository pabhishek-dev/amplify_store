package com.abhi.amplify_store.presentation.utils.components.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MI_CardImage(
    width: Int,
    height: Int
) {
    Card(
        modifier = Modifier
            .width(width.dp)
            .height(height.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize())
    }
}
