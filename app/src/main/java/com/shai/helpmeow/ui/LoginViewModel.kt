package com.shai.helpmeow.ui

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.preference.PreferenceManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.shai.helpmeow.data.ApiConfig
import com.shai.helpmeow.data.GeneralResponse
import com.shai.helpmeow.data.LoginRequest
import com.shai.helpmeow.data.LoginResponse
import com.shai.helpmeow.model.DataProgress
import com.shai.helpmeow.model.UserPreference
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val pref: UserPreference, private val context: Context) : ViewModel() {
    private val _login = MutableLiveData<DataProgress<String>>()
    val login: LiveData<DataProgress<String>> = _login

    private var email: String? = null
    private var username: String? = null

    fun login(email: String, password: String) {
        _login.postValue(DataProgress.Loading())
        val apiService = ApiConfig.getApiService()
        val request = LoginRequest(email, password)

        val loginCallback = object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                val user = response.body()
                val yourId = user?.yourId

                this@LoginViewModel.email = user?.email
                username = user?.username

                if (response.isSuccessful) {
                    val result = response.body()?.yourId
//                    val userId = response.yourId // asumsi Anda mendapatkan userId dari respons
                    if (yourId != null) {
                        saveUserUser(yourId)
                    }

                    val sharedPreferences =
                        PreferenceManager.getDefaultSharedPreferences(context)
                    val editor = sharedPreferences.edit()
                    editor.putString("email", email)
                    editor.putString("username", username)
                    editor.putString("yourId", yourId)
                    editor.apply()

//                    val sharedPreferences = getSharedPreferences("my_app_pref", Context.MODE_PRIVATE)
//                    val editor = sharedPreferences.edit()
//                    editor.putString("userId", yourId)
//                    editor.apply()
//
//                    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
//                    val editor = sharedPreferences.edit()
//                    editor.putString("yourId", yourId)
//                    editor.apply()

                    result?.let { saveUser(it) }
                    result?.let { saveUserId(it) }
                    _login.postValue(DataProgress.Success(result))
                } else {
                    val error = Gson().fromJson(
                        response.errorBody()?.charStream(), GeneralResponse::class.java
                    )
                    _login.postValue(DataProgress.Error(error.message))
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e(
                    LoginViewModel::class.java.simpleName, "onFailure login"
                )
                _login.postValue(DataProgress.Error(t.message))
            }
        }

        apiService.login(request).enqueue(loginCallback)
    }

    private fun saveUser(key: String) {
        viewModelScope.launch {
            pref.saveSession(key)
        }
    }

    private fun saveUserId(key: String) {
        viewModelScope.launch {
            pref.saveUserId(key)
        }
    }

//    fun saveUserId(context: Context, userId: String) {
//        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
//        val editor = sharedPreferences.edit()
//        editor.putString("USER_ID", userId)
//        editor.apply()
//    }

    private fun saveUserUser(userId: String) {
        val sharedPreferences = context.getSharedPreferences("MyApp", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("userId", userId)
        editor.apply()
    }
}