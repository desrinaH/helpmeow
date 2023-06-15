package com.example.helpmeow

data class CatDataPostItem(
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