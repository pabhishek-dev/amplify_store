package com.abhi.amplify_store.presentation.profile.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abhi.amplify_store.R
import com.abhi.amplify_store.presentation.utils.UiConstants.isPreview

@Composable
fun GridItem(
    modifier: Modifier = Modifier,
    icon: Int = R.drawable.baseline_home_24,
    text: String = ""
) {

    Column(
        modifier = modifier
            .width(90.dp)
            .border(
                border = BorderStroke(
                    width = 0.5.dp,
                    color = Color.Gray
                ),
                shape = RoundedCornerShape(size = 15.dp)
            )
            .padding(10.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = "Saved Address",
            tint = Color.LightGray
        )

        Text(
            text,
            modifier = Modifier.padding(top = 5.dp),
            maxLines = 2,
            color = if (isPreview()) Color.LightGray else Color.Black,
            style = TextStyle(fontSize = 13.sp)
        )
    }
}

@Preview
@Composable
fun GridItemPreview() {
    GridItem(

    )
}
