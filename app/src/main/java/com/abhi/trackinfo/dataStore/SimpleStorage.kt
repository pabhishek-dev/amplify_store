package com.abhi.trackinfo.dataStore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

// Create Datastore Instance
private val Context.dataStore by preferencesDataStore(name = "my_settings")

class SimpleStorage(private val context: Context) {

    // Define key
    private val USERNAME_KEY = stringPreferencesKey("username")

    // Read Data
    val userNameFlow = context.dataStore.data.map { preferences ->
        preferences[USERNAME_KEY] ?: "Guest"
    }

    // Write Data
    suspend fun saveUsername(name: String) {
        context.dataStore.edit { preferences ->
            preferences[USERNAME_KEY] = name
        }
    }
}
