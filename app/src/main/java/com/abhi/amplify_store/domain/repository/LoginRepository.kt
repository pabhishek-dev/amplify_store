package com.abhi.amplify_store.domain.repository

import com.abhi.amplify_store.domain.model.LoginResponse
import com.abhi.common.Result

interface LoginRepository {
    suspend fun login(email: String, password: String): Result<LoginResponse>

}