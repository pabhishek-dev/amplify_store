package com.abhi.amplify_store.presentation.authentication.util

import com.abhi.amplify_store.domain.model.User

sealed interface AuthUiEvent {
    data class ShowSnackBar(val message: String) : AuthUiEvent
    data class LoginSuccess(val user: User) : AuthUiEvent
    data class RegisterSuccess(val user: User) : AuthUiEvent
}
