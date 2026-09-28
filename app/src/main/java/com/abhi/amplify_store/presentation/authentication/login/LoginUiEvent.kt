package com.abhi.amplify_store.presentation.authentication.login

sealed interface LoginUiEvent {
    data object OnLogInClick: LoginUiEvent
    data object NavigateToRegister: LoginUiEvent
    data object NavigateToForgotPassword: LoginUiEvent
}