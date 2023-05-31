package com.example.helpmeow

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retro {
    fun getRetroClientInstance(): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl("https://backend-dot-helpmeow.et.r.appspot.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun getRegisterClientInstance(): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl("https://backend-dot-helpmeow.et.r.appspot.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

}