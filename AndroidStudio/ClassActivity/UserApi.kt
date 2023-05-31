package com.example.helpmeow

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST ("login/")
    fun login(
        @Body request: LoginRequest
    ): Call<LoginResponse>
}