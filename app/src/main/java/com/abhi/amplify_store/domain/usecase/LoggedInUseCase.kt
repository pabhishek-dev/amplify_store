package com.abhi.amplify_store.domain.usecase

import com.abhi.amplify_store.data.repository.UserPreferencesRepository

class LoggedInUseCase(
    private val userPreferencesRepository: UserPreferencesRepository
) {

    suspend operator fun invoke(){

    }
}
