package com.shai.helpmeow.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shai.helpmeow.model.UserPreference
import kotlinx.coroutines.launch

class SettingsViewModel(private val pref: UserPreference) : ViewModel() {
    fun logout() = deleteUser()

    private fun deleteUser() {
        viewModelScope.launch {
            pref.deleteSession()
        }
    }
}