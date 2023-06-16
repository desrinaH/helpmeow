package com.shai.helpmeow.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shai.helpmeow.data.ApiConfig
import com.shai.helpmeow.data.ProfileResponse
import com.shai.helpmeow.data.User
import com.shai.helpmeow.model.DataProgress
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel : ViewModel() {
    private val _profileResult = MutableLiveData<DataProgress<User>>()
    val profileResult: LiveData<DataProgress<User>> get() = _profileResult

    fun fetchProfileData(id: String) {
        _profileResult.value = DataProgress.Loading() // Set loading state

        val apiService = ApiConfig.getApiService()
        val call = apiService.getProfile(id)
        call.enqueue(object : Callback<ProfileResponse> {
            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
            ) {
                if (response.isSuccessful) {
                    val profileResponse = response.body()
                    if (profileResponse != null && !profileResponse.error!!) {
                        val user = profileResponse.user
                        _profileResult.value = DataProgress.Success(user)
                    } else {
                        val error = profileResponse?.message ?: "Unknown error occurred"
                        _profileResult.value = DataProgress.Error(error)
                    }
                } else {
                    val error = "Network request failed with code: ${response.code()}"
                        _profileResult.value = DataProgress.Error(error)
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                val error = "Network request failed: ${t.message}"
                _profileResult.value = DataProgress.Error(error)
            }
        })
    }
}

