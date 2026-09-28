package com.abhi.amplify_store.presentation.authentication.register

sealed interface RegisterUiEvent {
    data object OnRegisterClick: RegisterUiEvent
    data object NavigateToLogin: RegisterUiEvent
    data class ShowSnackBar(val message: String): RegisterUiEvent
}