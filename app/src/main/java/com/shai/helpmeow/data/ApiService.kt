package com.shai.helpmeow.data

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("register/")
    fun register(
        @Body request: RegisterRequest
    ): Call<GeneralResponse>

    @POST("login/")
    fun login(
        @Body request: LoginRequest
    ): Call<LoginResponse>

    @GET("home")
    fun getPost(
    ): Call<List<Post>>

    @Multipart
    @POST("create/image/{id}")
    fun addImage(
        @Path("id") userId: String,
        @Part file: MultipartBody.Part,
    ): Call<GeneralResponse>

    @GET("profile/{id}")
    fun getProfile(
        @Path("id") id: String
    ): Call<ProfileResponse>

    @Multipart
    @POST("/predict")
    fun getPredict(
        @Part file: MultipartBody.Part
    ): Call<PredictResponse>
}