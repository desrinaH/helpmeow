package com.shai.helpmeow.data

import com.google.gson.annotations.SerializedName

data class PredictResponse(
    @field:SerializedName("prediction")
    val prediction: String?,
//    @field:SerializedName("Percentage")
//    val percentage: String? = null,
    @field:SerializedName("error")
    val error: String?
)

data class ReturnResponse(
    var message: String,
    var status: Int,
)
