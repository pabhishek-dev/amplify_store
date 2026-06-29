package com.abhi.trackinfo.data.util.mapper

import com.abhi.trackinfo.data.model.LoginResponse
import com.abhi.trackinfo.domain.model.User

fun LoginResponse.toDomain(): User {

    return User(
        id = id,
        email = email,
        password = password
    )
}
