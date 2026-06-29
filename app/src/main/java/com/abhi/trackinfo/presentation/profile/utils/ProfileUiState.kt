package com.abhi.trackinfo.presentation.profile.utils

sealed class ProfileUiState {
    data object Idle: ProfileUiState()
    data object Loading: ProfileUiState()
}
