package com.example.helpmeow

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RegisterResponse {
    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("your_id")
    @Expose
    var yourId: String? = null

    @SerializedName("username")
    @Expose
    var username: String? = null

    @SerializedName("message")
    @Expose
    var message: String? = null
}