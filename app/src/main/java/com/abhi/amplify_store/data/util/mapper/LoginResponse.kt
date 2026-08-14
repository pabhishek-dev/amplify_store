package com.abhi.amplify_store.data.util.mapper

import com.abhi.amplify_store.data.model.LoginResponse
import com.abhi.amplify_store.domain.model.User

fun LoginResponse.toDomain(): User {

    return User(
        id = id,
        email = email,
        password = password
    )
}
