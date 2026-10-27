package com.abhi.network.api

import com.abhi.network.model.ProductResponse
import retrofit2.http.GET

interface HomeApiService {

    // Stream of data coming...
    @GET("/api/product/stream") // /stream
    suspend fun getHomeData(): List<ProductResponse>
}
