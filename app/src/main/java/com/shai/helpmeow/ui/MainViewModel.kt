package com.shai.helpmeow.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.shai.helpmeow.model.UserPreference
import kotlinx.coroutines.launch

class MainViewModel(private val pref: UserPreference) : ViewModel() {

    fun getUser() = pref.getSession().asLiveData()

    private fun saveUser(key: String) {
        viewModelScope.launch {
            pref.saveSession(key)
        }
    }

    fun logout() = deleteUser()

    private fun deleteUser() {
        viewModelScope.launch {
            pref.deleteSession()
        }
    }
}