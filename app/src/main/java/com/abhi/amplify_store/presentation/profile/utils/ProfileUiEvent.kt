package com.abhi.amplify_store.presentation.profile.utils

import com.abhi.amplify_store.domain.model.User

sealed interface ProfileUiEvent {
    data class ShowSnackBar(val message: String): ProfileUiEvent
    data class Success(val user: User): ProfileUiEvent
    data class Error(val message: String): ProfileUiEvent
}
