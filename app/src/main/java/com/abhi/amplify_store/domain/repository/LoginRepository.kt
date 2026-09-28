package com.abhi.amplify_store.domain.repository

import com.abhi.amplify_store.domain.model.LoginResponse
import com.abhi.amplify_store.domain.usecase.util.Result

interface LoginRepository {
    suspend fun login(email: String, password: String): Result<LoginResponse>

}