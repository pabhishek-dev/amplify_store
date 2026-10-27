package com.abhi.network.model

import com.google.gson.annotations.SerializedName

data class RegisterRequestDto(
    @SerializedName("email")
    val emailId: String,
    @SerializedName("password")
    val password: String
)