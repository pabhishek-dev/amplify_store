package com.abhi.trackinfo.data.remote

import com.abhi.trackinfo.data.model.HomeResponse
import retrofit2.http.GET

interface HomeApiService {

    // Stream of data coming...
    @GET("/stream")
    suspend fun getHomeData(): List<HomeResponse>
}
