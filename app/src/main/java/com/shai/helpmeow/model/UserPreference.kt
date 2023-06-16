package com.shai.helpmeow.model

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {

    private val USER_ID_KEY = stringPreferencesKey("user_id")

    fun getSession(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[SESSION] ?: ""
        }
    }

    suspend fun saveSession(token: String) {
        dataStore.edit { preferences ->
            preferences[SESSION] = token
        }
    }

    suspend fun deleteSession() {
        dataStore.edit { preferences ->
            preferences.remove(SESSION)
        }
    }

    suspend fun saveUserId(userId: String) {
        dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = userId
        }
    }

    suspend fun getUserId(): String? {
        val preferences = dataStore.data.first()
        return preferences[USER_ID_KEY]
    }


//    suspend fun saveUserId(userId: String) {
//        dataStore.edit { preferences ->
//            preferences[KEY_USER_ID] = userId
//        }
//    }
//
//    fun getUserId(): String? = runBlocking {
//        dataStore.data.map { preferences ->
//            preferences[KEY_USER_ID]
//        }.first()
//    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }

        private val SESSION = stringPreferencesKey("session")
    }
}