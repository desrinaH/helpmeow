package com.shai.helpmeow.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shai.helpmeow.data.ApiConfig
import com.shai.helpmeow.data.PredictResponse
import com.shai.helpmeow.data.ReturnResponse
import com.shai.helpmeow.model.UserPreference
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PredictViewModel(private val pref: UserPreference) : ViewModel()  {
    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean> = _showLoading

    private val _returnResponse = MutableLiveData<ReturnResponse>()
    val returnResponse: LiveData<ReturnResponse> = _returnResponse

    private val _dataPredict = MutableLiveData<PredictResponse>()
    val dataPredict: LiveData<PredictResponse> = _dataPredict

    fun getPredictPet(image: MultipartBody.Part) {
        _showLoading.value = true
        val service = ApiConfig.getApiServiceML().getPredict(file = image)
        service.enqueue(object : Callback<PredictResponse> {
            override fun onResponse(call: Call<PredictResponse>, response: Response<PredictResponse>) {
                val responses = response
                if(response.code() == 400){
                    _returnResponse.postValue(ReturnResponse(status = response.code(), message = response.message()))
                }else{
                    val responseBody = response.body()
                    if(responseBody?.error == null){
                        _dataPredict.postValue(responseBody!!)
                        _returnResponse.postValue(ReturnResponse(status = response.hashCode(), message = response.message()))
                    }else{
                        _returnResponse.postValue(ReturnResponse(status = response.hashCode(), message = response.body()?.error.toString()))
                    }
                }

                _showLoading.value = false
            }

            override fun onFailure(call: Call<PredictResponse>, t: Throwable) {
                _returnResponse.postValue(ReturnResponse(status = 500, message = t.message.toString()))
                _showLoading.value = false
            }
        })
    }
}