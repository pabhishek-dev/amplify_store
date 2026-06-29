package com.abhi.trackinfo.presentation.authentication.util

sealed class AuthUiState {
    data object Idle : AuthUiState()
    data object Loading : AuthUiState()
}
