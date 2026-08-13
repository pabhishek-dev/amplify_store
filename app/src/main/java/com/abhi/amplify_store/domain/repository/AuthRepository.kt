package com.abhi.amplify_store.domain.repository

import com.abhi.amplify_store.domain.model.User

interface AuthRepository {

    suspend fun login(email: String, password: String): User

    suspend fun register(email: String, password: String): User

}
