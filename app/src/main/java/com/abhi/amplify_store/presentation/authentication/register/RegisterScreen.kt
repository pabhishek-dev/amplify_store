package com.abhi.amplify_store.presentation.authentication.register

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abhi.amplify_store.presentation.authentication.components.UIScreen

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    snackBarHostState: SnackbarHostState,
    navigateToLogin: () -> Unit
    ) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when(effect){
                is RegisterUiEffect.NavigateToLogin -> {
                    navigateToLogin()
                }
                is RegisterUiEffect.ShowError -> {}
            }
        }
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState){ snackBarData ->
                Snackbar(
                    snackbarData = snackBarData,
                    containerColor = Color.DarkGray,
                    contentColor = Color.White,
              )
            }
        }
    ) { padding ->
        UIScreen(
            title = "Register",
            email = uiState.email,
            onEmailChange = { viewModel.onEmailChanged(it) },
            password = uiState.password,
            onPasswordChange = { viewModel.onPasswordChanged(it) },
            onSignIn = { navigateToLogin() },
            onSignUp = { viewModel.onEvent(RegisterUiEvent.OnRegisterClick) },
            agreement = uiState.agreement,
            onAgreementChanged = { viewModel.onAgreementChanged(it) },
            padding = padding
        )
    }

}