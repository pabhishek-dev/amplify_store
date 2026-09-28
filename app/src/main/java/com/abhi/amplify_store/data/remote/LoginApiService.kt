package com.abhi.amplify_store.data.remote

import com.abhi.amplify_store.data.model.LoginRequestDto
import com.abhi.amplify_store.data.model.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {

    @POST("/api/auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequestDto
    ): LoginResponseDto

}