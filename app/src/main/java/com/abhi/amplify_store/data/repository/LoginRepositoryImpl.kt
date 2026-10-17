package com.abhi.amplify_store.data.repository

import com.abhi.amplify_store.domain.model.LoginResponse
import com.abhi.amplify_store.data.util.mapper.toDomain
import com.abhi.amplify_store.domain.repository.LoginRepository
import com.abhi.network.SafeApiCall
import com.abhi.network.api.LoginApiService
import com.abhi.network.model.LoginRequestDto
import javax.inject.Inject
import com.abhi.common.Result

class LoginRepositoryImpl @Inject constructor(
    private val loginApiService: LoginApiService,
): LoginRepository {
    override suspend fun login(email: String, password: String): Result<LoginResponse> {
        return SafeApiCall(
            apiCall = { loginApiService.login(LoginRequestDto(email, password)) },
            mapper = { dto -> dto.toDomain() }
        )
    }
}