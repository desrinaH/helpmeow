package com.shai.helpmeow.ui

import android.content.Context
import android.preference.PreferenceManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    private val _userId = MutableLiveData<String>()
    val userId: LiveData<String>
        get() = _userId

//    fun saveUserId(userId: String) {
//        _userId.value = userId
//    }
    fun saveUserId(context: Context, userId: String) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = sharedPreferences.edit()
        editor.putString("USER_ID", userId)
        editor.apply()
    }
}

//fun saveUserId(context: Context, userId: String) {
//    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
//    val editor = sharedPreferences.edit()
//    editor.putString("USER_ID", userId)
//    editor.apply()
//}
