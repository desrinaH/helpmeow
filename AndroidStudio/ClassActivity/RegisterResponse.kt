package com.example.helpmeow

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RegisterResponse {
    @SerializedName("username")
    @Expose
    var username: String? = null
}