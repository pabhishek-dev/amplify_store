package com.abhi.amplify_store.data.util.mapper

import com.abhi.amplify_store.domain.model.LoginResponse
import com.abhi.network.model.LoginResponseDto

fun LoginResponseDto.toDomain(): LoginResponse {

    return LoginResponse(
        accessToken = accessToken,
        tokenType = tokenType
    )
}
