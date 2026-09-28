package com.abhi.amplify_store.presentation.authentication.login

import com.abhi.amplify_store.domain.model.LoginResponse

sealed interface LoginUiEffect {
    data class NavigateToHome(val loginResponse: LoginResponse): LoginUiEffect
    data class ShowSnackBar(val message: String): LoginUiEffect
    data object NavigateToRegister: LoginUiEffect
    data object NavigateToForgotPassword: LoginUiEffect
}