package com.abhi.amplify_store.data.remote

import com.abhi.amplify_store.data.model.SSLResponse
import retrofit2.http.GET

interface SSLApiService {

    @GET("/")
    suspend fun getData(): SSLResponse
}
