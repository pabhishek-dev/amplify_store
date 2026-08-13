package com.abhi.amplify_store.presentation.profile.utils

sealed class ProfileUiState {
    data object Idle: ProfileUiState()
    data object Loading: ProfileUiState()
}
