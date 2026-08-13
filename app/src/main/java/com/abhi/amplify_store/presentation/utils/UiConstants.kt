package com.abhi.amplify_store.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.abhi.amplify_store.R

object UiConstants {

    val Gradient: (Int) -> Brush = { page ->
        val colors = when (page) {
            1 -> listOf(Color.Black, Color.DarkGray)
            2 -> listOf(Color.Magenta, Color.Red)
            3 -> listOf(Color.Cyan, Color.Blue)
            4 -> listOf(Color.Blue, Color(0xFF00C6FF))
            else -> listOf(Color.DarkGray, Color.Black)
        }
        Brush.verticalGradient(colors)
    }

    val key = "Tr3LZgNJoVCyB746Pke4WbxCrVAMOOSe1BPtanlcjsc="

    // Font Configurations

    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )

    val ubuntuFont = FontFamily(
        Font(
            googleFont = GoogleFont("Indie Flower"),
            fontProvider = provider
        )
    )

    val baseTextStyle = TextStyle(
        fontFamily = ubuntuFont
    )

    const val LOGS = "AMPLIFYSTORE_LOGS"

    @Composable
    fun isPreview(): Boolean = LocalInspectionMode.current

}
