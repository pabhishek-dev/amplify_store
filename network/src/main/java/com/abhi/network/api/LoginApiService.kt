package com.abhi.network.api

import com.abhi.network.model.LoginRequestDto
import com.abhi.network.model.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {

    @POST("/api/auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequestDto
    ): LoginResponseDto

}