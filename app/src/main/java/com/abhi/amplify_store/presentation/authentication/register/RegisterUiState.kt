package com.abhi.amplify_store.presentation.authentication.register

data class RegisterUiState(
    val email: String = "",
    val password: String = "",
    val agreement: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)