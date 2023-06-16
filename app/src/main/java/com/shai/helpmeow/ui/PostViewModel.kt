package com.shai.helpmeow.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.shai.helpmeow.data.ApiConfig
import com.shai.helpmeow.data.GeneralResponse
import com.shai.helpmeow.model.DataProgress
import com.shai.helpmeow.model.UserPreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody

class PostViewModel(private val pref: UserPreference, private val context: Context) : ViewModel() {

    private val _story = MutableLiveData<DataProgress<String>>()
    val story: LiveData<DataProgress<String>> = _story

    suspend fun setStory(
        userId: String,
        imageMultipart: MultipartBody.Part
    ) {
        _story.postValue(DataProgress.Loading())

        val userId = getUserId()

        val apiService = ApiConfig.getApiService()
        val client = apiService.addImage(userId, imageMultipart)

        withContext(Dispatchers.IO) {

            try {
                val response = client.execute()

                if (response.isSuccessful) {
                    _story.postValue(DataProgress.Success(response.body()?.message))
                } else {
                    val errorResponse = Gson().fromJson(
                        response.errorBody()?.charStream(),
                        GeneralResponse::class.java
                    )
                    _story.postValue(DataProgress.Error(errorResponse.message))
                }
            } catch (t: Throwable) {
                Log.e(PostViewModel::class.java.simpleName, "onFailure upload")
                _story.postValue(DataProgress.Error(t.message))
            }

        }
    }

    private fun getUserId(): String {
        val sharedPreferences = context.getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        return sharedPreferences.getString("userId", "") ?: ""
    }
}

