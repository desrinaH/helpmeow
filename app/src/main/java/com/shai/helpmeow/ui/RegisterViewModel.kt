package com.shai.helpmeow.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.shai.helpmeow.data.ApiConfig
import com.shai.helpmeow.data.GeneralResponse
import com.shai.helpmeow.data.RegisterRequest
import com.shai.helpmeow.model.DataProgress
import com.shai.helpmeow.model.UserPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel(private val pref: UserPreference) : ViewModel() {
    private val _register = MutableLiveData<DataProgress<String>>()
    val register: LiveData<DataProgress<String>> = _register

    fun register(name: String, email: String, password: String) {
        _register.postValue(DataProgress.Loading())
        val client = ApiConfig.getApiService().register(RegisterRequest(name, email, password))

        client.enqueue(object : Callback<GeneralResponse> {
            override fun onResponse(call: Call<GeneralResponse>, response: Response<GeneralResponse>) {
                if (response.isSuccessful) {
                    val message = response.body()?.message.toString()
                    _register.postValue(DataProgress.Success(message))
                } else {
                    val errorResponse = Gson().fromJson(
                        response.errorBody()?.charStream(),
                        GeneralResponse::class.java
                    )
                    _register.postValue(DataProgress.Error(errorResponse.message))
                }
            }

            override fun onFailure(call: Call<GeneralResponse>, t: Throwable) {
                Log.e(
                    RegisterViewModel::class.java.simpleName,
                    "onFailure register"
                )
                _register.postValue(DataProgress.Error(t.message))
            }
        })
    }

}