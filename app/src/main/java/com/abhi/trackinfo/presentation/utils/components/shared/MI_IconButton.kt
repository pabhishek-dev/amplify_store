package com.abhi.trackinfo.presentation.utils.components.shared

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun MI_IconButton(
    moduleName: String,
    icon: Int,
    iconColor: Color = Color.Black,
    label: String = "",
    isSelected: Boolean = false,
    onClick: () -> Unit
) {

    val activeColor = if (isSelected) Color.Blue else iconColor

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
        )
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = moduleName,
            tint = activeColor,
            modifier = Modifier.size(24.dp)
        )
        if (label.isNotEmpty()) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = label,
                color = activeColor,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}
