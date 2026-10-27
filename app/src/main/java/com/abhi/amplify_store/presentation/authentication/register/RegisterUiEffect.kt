package com.abhi.amplify_store.presentation.authentication.register

sealed interface RegisterUiEffect {
    data object NavigateToLogin: RegisterUiEffect
    data class ShowError(val message: String?): RegisterUiEffect
}