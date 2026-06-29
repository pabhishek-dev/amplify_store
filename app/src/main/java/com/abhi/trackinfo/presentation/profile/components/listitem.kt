package com.abhi.trackinfo.presentation.profile.components

import android.graphics.pdf.models.ListItem
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abhi.trackinfo.presentation.utils.UiConstants.ubuntuFont

@Composable
fun ListItem(
    tileName: String,
    tileLogo: Int = 0) {
    Row(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 7.dp)
            .fillMaxWidth()
    ) {
        Icon(
            modifier = Modifier
                .weight(0.5f),
            imageVector = Icons.AutoMirrored.Default.Chat,
            contentDescription = "leftIcon",
            tint = Color.Black
        )

        Text(
            modifier = Modifier
                .weight(2f)
                .align(alignment = Alignment.CenterVertically),
            text = tileName,
            style = TextStyle(
                color = Color.Black,
                fontFamily = ubuntuFont
            )
        )

        Icon(
            modifier = Modifier
                .weight(0.5f),
            imageVector = Icons.AutoMirrored.Default.ArrowRight,
            contentDescription = "rightIcon",
            tint = Color.Black
        )
    }
}

@Preview
@Composable
fun ListItemPreview(){
    ListItem("default")
}
