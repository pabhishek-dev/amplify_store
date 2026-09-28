package com.abhi.amplify_store.data.util.mapper

import com.abhi.amplify_store.data.model.RegisterResponseDto
import com.abhi.amplify_store.domain.model.RegisterResponse

fun RegisterResponseDto.toDomain(): RegisterResponse{

    return RegisterResponse(
        id = id,
        message = message
    )
}