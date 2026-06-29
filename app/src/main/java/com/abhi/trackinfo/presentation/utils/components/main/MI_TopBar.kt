package com.abhi.trackinfo.presentation.utils.components.main

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import com.abhi.trackinfo.R
import com.abhi.trackinfo.presentation.utils.UiConstants.baseTextStyle
import com.abhi.trackinfo.ui.theme.White80
import com.abhi.trackinfo.ui.theme.White90

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MI_TopBar(
    context: Context,
    scrollBehaviour: TopAppBarScrollBehavior,
    isVisible: Boolean
) {

    val titleFontSize = if (isVisible) {
        lerp(35.sp, 20.sp, scrollBehaviour.state.collapsedFraction)
    } else {
        20.sp
    }

    val colors = TopAppBarDefaults.topAppBarColors(
        containerColor = White80,
        scrolledContainerColor = White90,
        navigationIconContentColor = Color.Black,
        titleContentColor = Color.Black,
        actionIconContentColor = Color.Black
    )

    val navigationIcon: @Composable () -> Unit = {
        IconButton(onClick = {
            Toast.makeText(context, "Menu", Toast.LENGTH_SHORT).show()
        }) {
            Icon(
                painter = painterResource(R.drawable.outline_menu_24),
                contentDescription = "Menu",
                tint = Color.Black
            )
        }
    }

    val actions: @Composable RowScope. () -> Unit = {
        IconButton(onClick = {
            Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show()
        }) {
            Icon(
                painter = painterResource(R.drawable.baseline_settings_24),
                contentDescription = "Settings",
                tint = Color.Black
            )
        }
    }

    val title: @Composable () -> Unit = {
        Row(horizontalArrangement = Arrangement.Center) {
            Text(
                text = "Good morning! ",
                style = baseTextStyle.copy(
                    fontSize = titleFontSize,
                    lineHeight = titleFontSize,
                    fontFamily = FontFamily(Font(R.font.opensans_medium))
                )
            )
        }
    }

    if (isVisible) {
        LargeTopAppBar(
            title = title,
            colors = colors,
            scrollBehavior = scrollBehaviour,
            navigationIcon = navigationIcon,
            actions = actions
        )
    } else {
        TopAppBar(
            title = title,
            colors = colors,
            scrollBehavior = null,
            navigationIcon = navigationIcon,
            actions = actions
        )
    }
}
