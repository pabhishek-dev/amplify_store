package com.abhi.amplify_store.presentation.authentication.login

data class LoginUiState (
  val email: String = "",
  val password: String = "",
  val agreement: Boolean = false,
  val rememberMe: Boolean = false,
  val isLoading: Boolean = false,
  val errorMessage: String? = null
)
