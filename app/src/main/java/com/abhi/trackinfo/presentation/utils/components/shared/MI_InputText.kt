package com.abhi.trackinfo.presentation.utils.components.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

/**
 * Input Text Box
 * @param inputValue
 * @param placeHolder
 * */

@Composable
fun MI_InputText(
    modifier: Modifier = Modifier,
    inputValue: String,
    onValueChanged: (String) -> Unit,
    label: String = "",
    placeHolder: String,
    isError: Boolean = false,
    isPassword: Boolean = false
) {
    OutlinedTextField(
        value = inputValue,
        onValueChange = onValueChanged,
        label = { if (label.isNotEmpty()) Text(label) },
        placeholder = { if (placeHolder.isNotEmpty()) Text(placeHolder) },
        isError = isError,
        modifier = modifier.fillMaxWidth(),
        visualTransformation = if (isPassword) PasswordVisualTransformation()
        else VisualTransformation.None
    )
}

@Preview
@Composable
fun MIInputTextPreview() {
    MI_InputText(
        inputValue = "",
        onValueChanged = {},
        placeHolder = ""
    )
}
