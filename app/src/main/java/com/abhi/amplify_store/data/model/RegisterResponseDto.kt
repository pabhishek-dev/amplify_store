package com.abhi.amplify_store.data.model

import com.google.gson.annotations.SerializedName

data class RegisterResponseDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("message")
    val message: String
)