package com.example.helpmeow

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterApi {
    @POST("register/")
    fun register(
        @Body request: RegisterRequest
    ): Call<RegisterResponse>
}