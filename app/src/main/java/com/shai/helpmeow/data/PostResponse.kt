package com.shai.helpmeow.data

import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: List<Post>,
)

//@Parcelize
//data class Post(
//    @SerializedName("id")
//    val id: String,
//    @SerializedName("photo")
//    val photo: String,
//    @SerializedName("name")
//    val name: String,
//    @SerializedName("role")
//    val role: String,
//    @SerializedName("breed")
//    val breed: String,
//    @SerializedName("gender")
//    val gender: String,
//    @SerializedName("description")
//    val description: String,
//    @SerializedName("location")
//    val location: String,
//    @SerializedName("latitude")
//    val latitude: Double,
//    @SerializedName("longitude")
//    val longitude: Double,
//    val status: String,
//    val upload_by_username: String,
//    val upload_by_email: String
//) : Parcelable

data class Post(
    val breed: String,
    val created_at: String,
    val description: String,
    val embedding: String,
    val gender: String,
    val id: Int,
    val latitude: Double,
    val location: String,
    val longitude: Double,
    val name: String,
    val photo: String,
    val role: String,
    val status: String,
    val upload_by_username: String,
    val upload_by_email: String
)
