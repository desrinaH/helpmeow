package com.shai.helpmeow.model

sealed class DataProgress<out T>(
    val resultData: T? = null,
    val errorMessage: String? = null,
) {
    class Success<out T>(resultData: T?) : DataProgress<T>(resultData)
    class Error<out T>(errorMessage: String?, resultData: T? = null) :
        DataProgress<T>(resultData, errorMessage)

    class Loading<out T>(resultData: T? = null) : DataProgress<T>(resultData)
}