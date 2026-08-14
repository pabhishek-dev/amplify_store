package com.abhi.amplify_store.presentation.utils.components.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun MI_CheckBox(
    checkValue: Boolean,
    onChecked: (Boolean) -> Unit,
    text: String
) {
    Row(

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Checkbox(
            checked = checkValue,
            onCheckedChange = onChecked,
        )
        Text(text)
    }
}
