package com.abhi.amplify_store.domain.repository

import com.abhi.amplify_store.domain.model.RegisterResponse
import com.abhi.common.Result

interface RegisterRepository {
    suspend fun register(email: String, password: String): Result<RegisterResponse>
}