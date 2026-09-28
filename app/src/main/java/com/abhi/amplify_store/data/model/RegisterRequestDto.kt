package com.abhi.amplify_store.data.model

import com.google.gson.annotations.SerializedName

data class RegisterRequestDto(
    @SerializedName("email")
    val emailId: String,
    @SerializedName("password")
    val password: String
)