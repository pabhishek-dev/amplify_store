package com.abhi.trackinfo.presentation.authentication.util

import com.abhi.trackinfo.domain.model.User

sealed interface AuthUiEvent {
    data class ShowSnackBar(val message: String) : AuthUiEvent
    data class LoginSuccess(val user: User) : AuthUiEvent
    data class RegisterSuccess(val user: User) : AuthUiEvent
}
