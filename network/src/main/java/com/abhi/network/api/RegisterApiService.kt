package com.abhi.network.api

import com.abhi.network.model.RegisterRequestDto
import com.abhi.network.model.RegisterResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterApiService {

    @POST("/api/auth/register")
    suspend fun register(
        @Body registerRequestDto: RegisterRequestDto
    ): RegisterResponseDto

}