package com.abhi.trackinfo.presentation.utils.components.shared

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MI_IconButton(
    moduleName: String,
    icon: Int,
    iconColor: Color = Color.Black,
    label: String = "",
    isSelected: Boolean = false,
    onClick: () -> Unit
) {

    val color = if(isSelected) Color.Blue else iconColor

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(
                onClick = onClick
        )
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = moduleName,
            tint = color
        )
        Text(
            text = label,
            color = color,
            fontSize = 12.sp
        )
    }
}
