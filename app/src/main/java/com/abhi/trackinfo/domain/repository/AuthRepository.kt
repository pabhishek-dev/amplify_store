package com.abhi.trackinfo.domain.repository

import com.abhi.trackinfo.domain.model.User

interface AuthRepository {

    suspend fun login(email: String, password: String): User

    suspend fun register(email: String, password: String): User

}
