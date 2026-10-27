package com.abhi.amplify_store.data.repository

import com.abhi.amplify_store.data.util.mapper.toDomain
import com.abhi.amplify_store.domain.model.RegisterResponse
import com.abhi.amplify_store.domain.repository.RegisterRepository
import com.abhi.network.api.RegisterApiService
import com.abhi.network.model.RegisterRequestDto
import com.abhi.common.Result
import com.abhi.network.SafeApiCall


class RegisterRepositoryImpl(
    private val registerApiService: RegisterApiService
): RegisterRepository {
    override suspend fun register(email: String, password: String): Result<RegisterResponse> {
        return SafeApiCall(
            apiCall = { registerApiService.register(RegisterRequestDto(email, password)) },
            mapper = { dto -> dto.toDomain() }
        )
    }
}