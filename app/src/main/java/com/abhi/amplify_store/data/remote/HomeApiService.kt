package com.abhi.amplify_store.data.remote

import com.abhi.amplify_store.data.model.ProductResponse
import retrofit2.http.GET

interface HomeApiService {

    // Stream of data coming...
    @GET("/api/product/stream") // /stream
    suspend fun getHomeData(): List<ProductResponse>
}
