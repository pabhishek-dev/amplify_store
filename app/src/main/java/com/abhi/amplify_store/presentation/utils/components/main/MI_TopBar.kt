package com.abhi.amplify_store.presentation.utils.components.main

import android.content.Context
import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import com.abhi.amplify_store.R
import com.abhi.amplify_store.presentation.utils.UiConstants.baseTextStyle
import com.abhi.amplify_store.ui.theme.White80
import com.abhi.amplify_store.ui.theme.White90

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MI_TopBar(
    context: Context,
    scrollBehaviour: TopAppBarScrollBehavior
) {
    val titleFontSize = lerp(35.sp, 20.sp, scrollBehaviour.state.collapsedFraction)

    LargeTopAppBar(
        title = {
            Text(
                text = "Good morning! ",
                style = baseTextStyle.copy(
                    fontSize = titleFontSize,
                    lineHeight = titleFontSize,
                    fontFamily = FontFamily(Font(R.font.opensans_medium))
                )
            )
        },
        navigationIcon = {
            IconButton(onClick = { Toast.makeText(context, "Menu", Toast.LENGTH_SHORT).show() }) {
                Icon(
                    painter = painterResource(R.drawable.outline_menu_24),
                    contentDescription = "Menu"
                )
            }
        },
        actions = {
            IconButton(onClick = { Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show() }) {
                Icon(
                    painter = painterResource(R.drawable.baseline_settings_24),
                    contentDescription = "Settings"
                )
            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = White80,
            scrolledContainerColor = White90,
            navigationIconContentColor = Color.Black,
            titleContentColor = Color.Black,
            actionIconContentColor = Color.Black
        ),
        scrollBehavior = scrollBehaviour
    )
}