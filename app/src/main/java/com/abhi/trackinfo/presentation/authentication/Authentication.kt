package com.abhi.trackinfo.presentation.authentication

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.abhi.trackinfo.presentation.authentication.components.UIScreen
import com.abhi.trackinfo.presentation.authentication.util.AuthUiEvent

@Composable
fun Authentication(
    modifier: Modifier = Modifier,
    viewModel: AuthenticationViewModel = hiltViewModel(),
    onAuthSuccess: () -> Unit = {},
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() }
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var agreement by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is AuthUiEvent.LoginSuccess -> {
                    focusManager.clearFocus()
                    email = ""
                    password = ""

                    snackBarHostState.showSnackbar("Welcome back, ${event.user.email}")
                    onAuthSuccess()
                }
                is AuthUiEvent.RegisterSuccess -> {
                    focusManager.clearFocus()
                    email = ""
                    password = ""

                    snackBarHostState.showSnackbar("Welcome, ${event.user.email}")
                }
                is AuthUiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    UIScreen(
        email = email,
        onEmailChange = { email = it },
        password = password,
        onPasswordChange = { password = it },
        onSignIn = { email, password -> viewModel.login(email, password) },
        // onSignUp = { email, password -> viewModel.register(email, password) },
        onSignUp = { email, password -> viewModel.register(email, password) },
        agreement = agreement,
        onAgreementChanged = { agreement = it },
        rememberMe = rememberMe,
        onRememberChange = { remember -> rememberMe = remember }
    )
}


@Preview
@Composable
fun AuthenticationScreenPreview() {
    Authentication(

    )
}
