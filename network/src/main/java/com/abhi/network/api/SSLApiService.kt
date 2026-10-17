package com.abhi.network.api

import com.abhi.network.model.SSLResponse
import retrofit2.http.GET

interface SSLApiService {

    @GET("/")
    suspend fun getData(): SSLResponse
}
