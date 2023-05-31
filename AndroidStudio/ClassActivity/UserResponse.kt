package com.example.helpmeow

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginResponse {
    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("your_id")
    @Expose
    var yourId: String? = null
}