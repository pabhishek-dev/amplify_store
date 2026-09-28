package com.abhi.amplify_store.data.util.mapper

import com.abhi.amplify_store.data.model.LoginResponseDto
import com.abhi.amplify_store.domain.model.LoginResponse

fun LoginResponseDto.toDomain(): LoginResponse {

    return LoginResponse(
        accessToken = accessToken,
        tokenType = tokenType
    )
}
