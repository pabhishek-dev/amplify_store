package com.abhi.amplify_store.data.remote

import com.abhi.amplify_store.data.model.RegisterRequestDto
import com.abhi.amplify_store.data.model.RegisterResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterApiService {

    @POST("/api/auth/register")
    suspend fun register(
        @Body registerRequestDto: RegisterRequestDto
    ): RegisterResponseDto

}