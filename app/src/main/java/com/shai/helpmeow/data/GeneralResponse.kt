package com.shai.helpmeow.data

import com.google.gson.annotations.SerializedName

data class GeneralResponse(
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("message")
    val message: String?,
)
