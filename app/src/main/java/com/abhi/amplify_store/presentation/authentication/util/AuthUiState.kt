package com.abhi.amplify_store.presentation.authentication.util

sealed class AuthUiState {
    data object Idle : AuthUiState()
    data object Loading : AuthUiState()
}
