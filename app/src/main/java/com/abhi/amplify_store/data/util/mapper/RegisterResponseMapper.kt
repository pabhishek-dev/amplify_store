package com.abhi.amplify_store.data.util.mapper

import com.abhi.amplify_store.domain.model.RegisterResponse
import com.abhi.network.model.RegisterResponseDto

fun RegisterResponseDto.toDomain(): RegisterResponse{

    return RegisterResponse(
        id = id,
        message = message
    )
}