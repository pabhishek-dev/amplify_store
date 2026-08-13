package com.abhi.amplify_store.presentation.utils.components.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MI_TitleText(
    titleValue: String,
    fontSize: TextUnit = 20.sp
) {
    Text(
        text = titleValue,
        fontSize = fontSize,
        color = Color.DarkGray,
        style = TextStyle(
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onSurface,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp),
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
fun MITitleTextPreview() {
    MI_TitleText("Welcome")
}
