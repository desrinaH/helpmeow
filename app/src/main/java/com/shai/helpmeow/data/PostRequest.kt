package com.shai.helpmeow.data

import com.google.gson.annotations.SerializedName
import java.io.File

data class PostRequest(
    @field:SerializedName("file")
    val file: File,
    @SerializedName("name")
    val name: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("breed")
    val breed: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String,
)
