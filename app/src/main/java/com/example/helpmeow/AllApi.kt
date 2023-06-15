package com.example.helpmeow

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LoginApi {
    @POST ("login")
    fun login(
        @Body request: LoginRequest
    ): Call<LoginResponse>
}

interface RegisterApi {
    @POST("register")
    fun register(
        @Body request: RegisterRequest
    ): Call<RegisterResponse>
}

interface LogoutApi {
    @GET("logout/{id}")
    fun logout(@Path("id") id: String): Call<LogoutResponse>
}

interface CatApi {
    @GET("home")
    fun getData(): Call<List<CatDataPostItem>>
}