package com.abhi.amplify_store.presentation.authentication.login

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abhi.amplify_store.presentation.authentication.components.UIScreen
import com.abhi.amplify_store.presentation.utils.Messages.LOGIN_SUCCESSFUL

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    onLoginSuccess: () -> Unit = {},
    onRegisterClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is LoginUiEffect.NavigateToHome -> {
                    focusManager.clearFocus()
                    snackBarHostState.showSnackbar(LOGIN_SUCCESSFUL)
                    onLoginSuccess()
                }
                is LoginUiEffect.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(effect.message)
                }
                is LoginUiEffect.NavigateToRegister -> Unit
                is LoginUiEffect.NavigateToForgotPassword -> Unit
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState) { snackBarData ->
                Snackbar(
                    snackbarData = snackBarData,
                    containerColor = Color.DarkGray,
                    contentColor = Color.White,
                )
            }
        },
    ) { padding ->
        UIScreen(
            title = "Login",
            email = uiState.email,
            onEmailChange = { viewModel.onEmailChanged(it) },
            password = uiState.password,
            onPasswordChange = { viewModel.onPasswordChanged(it) },
            onSignIn = { viewModel.onEvent(LoginUiEvent.OnLogInClick) },
            onSignUp = { onRegisterClick() },
            agreement = uiState.agreement,
            onAgreementChanged = { viewModel.onAgreementChanged(it) },
            rememberMe = uiState.rememberMe,
            onRememberChange = { viewModel.onRememberMeChanged(it) },
            padding = padding
        )
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    UIScreen(
        title = "Login",
        email = "",
        onEmailChange = { },
        password = "",
        onPasswordChange = { },
        onSignIn = { },
        onSignUp = { },
        agreement = true,
        onAgreementChanged = { },
        rememberMe = true,
        onRememberChange = { },
        padding = PaddingValues(1.dp)
    )
}
