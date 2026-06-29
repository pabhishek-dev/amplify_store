package com.abhi.trackinfo.domain.usecase

import com.abhi.trackinfo.data.repository.UserPreferencesRepository

class LoggedInUseCase(
    private val userPreferencesRepository: UserPreferencesRepository
) {

    suspend operator fun invoke(){

    }
}
