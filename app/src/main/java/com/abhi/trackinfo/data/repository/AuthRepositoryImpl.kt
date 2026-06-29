package com.abhi.trackinfo.data.repository

import com.abhi.trackinfo.data.local.dao.UserDao
import com.abhi.trackinfo.data.local.entities.UserEntity
import com.abhi.trackinfo.domain.model.User
import com.abhi.trackinfo.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val userDao: UserDao
) : AuthRepository {
    override suspend fun login(email: String, password: String): User {
        return userDao.login(email, password).let {
            User(
                id = it.id,
                email = it.email,
                password = it.password
            )
        }
    }

    override suspend fun register(
        email: String,
        password: String
    ): User {
        val entity = UserEntity(email = email, password = password)
        val insertId = userDao.register(entity)
        return User(id = insertId?.toInt() ?: 0, email = email, password = password)
    }
}
