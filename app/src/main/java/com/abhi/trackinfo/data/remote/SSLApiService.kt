package com.abhi.trackinfo.data.remote

import com.abhi.trackinfo.data.model.SSLResponse
import retrofit2.http.GET

interface SSLApiService {

    @GET("/")
    suspend fun getData(): SSLResponse
}
