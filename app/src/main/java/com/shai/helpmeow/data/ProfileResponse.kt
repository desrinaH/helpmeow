package com.shai.helpmeow.data

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("user")
    val user: User
)

data class User(
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String
)