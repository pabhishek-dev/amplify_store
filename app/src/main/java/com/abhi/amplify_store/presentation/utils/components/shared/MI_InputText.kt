package com.abhi.amplify_store.presentation.utils.components.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

/**
 * Input text can handle username and pwd field
 */
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

    var isPasswordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = inputValue,
        onValueChange = onValueChanged,
        label = if (label.isNotEmpty()) {{ Text(label) }} else null ,
        placeholder = if (placeHolder.isNotEmpty()) {{ Text(placeHolder) }} else null,
        isError = isError,
        modifier = modifier.fillMaxWidth(),
        visualTransformation = if (isPassword && !isPasswordVisible)
            PasswordVisualTransformation()
        else
            VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            if (isPassword && inputValue.isNotEmpty()) {
                val image = if (isPasswordVisible) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }
                val description = if (isPasswordVisible) "Hide password" else "Show password"
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(imageVector = image, contentDescription = description)
                }
            } else null
        }
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
