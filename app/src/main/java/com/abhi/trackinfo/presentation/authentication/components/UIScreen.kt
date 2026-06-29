package com.abhi.trackinfo.presentation.authentication.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abhi.trackinfo.presentation.utils.components.shared.MI_Button
import com.abhi.trackinfo.presentation.utils.components.shared.MI_CheckBox
import com.abhi.trackinfo.presentation.utils.components.shared.MI_InputText
import com.abhi.trackinfo.presentation.utils.components.shared.MI_Switch
import com.abhi.trackinfo.presentation.utils.components.shared.MI_TitleText

/**
 * Auth Screen to display user inputs to accept email and password
 * @Param email
 * @Param password
 * */

@Composable
fun UIScreen(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onSignIn: (String, String) -> Unit = { _, _ -> },
    onSignUp: (String, String) -> Unit = { _, _ -> },
    agreement: Boolean,
    onAgreementChanged: (Boolean) -> Unit,
    rememberMe: Boolean,
    onRememberChange: (Boolean) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
                .wrapContentHeight(),
            colors = CardDefaults
                .cardColors(
                    MaterialTheme.colorScheme.surface,
                ),
            elevation = CardDefaults
                .cardElevation(
                    defaultElevation = 10.dp,
                    focusedElevation = 10.dp,
                    pressedElevation = 10.dp
                )

        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                MI_TitleText(
                    titleValue = "Login"
                )

                Spacer(Modifier.height(20.dp))

                MI_InputText(
                    inputValue = email,
                    onValueChanged = { onEmailChange(it) },
                    label = "username",
                    placeHolder = "enter valid username",
                )

                Spacer(Modifier.height(20.dp))

                MI_InputText(
                    inputValue = password,
                    onValueChanged = { onPasswordChange(it) },
                    label = "password",
                    placeHolder = "enter valid password",
                    isPassword = true
                )

                Spacer(Modifier.height(20.dp))


                Row(

                ) {

                    MI_CheckBox(
                        checkValue = agreement,
                        onChecked = onAgreementChanged,
                        text = "I Agree"
                    )

                    Spacer(Modifier.width(40.dp))

                    MI_Switch(
                        check = rememberMe,
                        onChecked = onRememberChange,
                        text = "Remember Me"
                    )

                }



                Spacer(Modifier.height(20.dp))

                Row {
                    MI_Button(
                        text = "Sign In",
                        onClick = {
                            onSignIn(email, password)
                        }
                    )

                    Spacer(Modifier.width(20.dp))

                    MI_Button(
                        text = "Sign Up",
                        onClick = {
                            onSignUp(email, password)
                        }
                    )

                }
            }
        }
    }
}

@Preview
@Composable
fun UIScreenPreview() {
    UIScreen(
        email = "abc@gmail.com",
        onEmailChange = {},
        password = "abhishek",
        onPasswordChange = {},
        agreement = true,
        onAgreementChanged = {},
        rememberMe = true,
        onRememberChange = {}
    )
}
