package com.abhi.trackinfo.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    private object Keys {
        val AUTH_TOKEN = stringPreferencesKey("auth_token")
        val USER_ID = stringPreferencesKey("user_id")
        val DARK_THEME = booleanPreferencesKey("dark_theme")
        val ONBOARDING_DONE = booleanPreferencesKey("onboarding_done")

        val SAVE_EMAIL = stringPreferencesKey("save_email")
    }

    private val preferencesFlow: Flow<Preferences> = dataStore.data
        .catch { e ->
            if (e is IOException) emit(emptyPreferences()) else throw e
        }

    val authToken: Flow<String?> = preferencesFlow.map { it[Keys.AUTH_TOKEN] }
    val isLoggedIn: Flow<Boolean> = preferencesFlow.map { it[Keys.AUTH_TOKEN] != null }
    val darkTheme: Flow<Boolean> = preferencesFlow.map { it[Keys.DARK_THEME] ?: false }
    val onboardingDone: Flow<Boolean> = preferencesFlow.map { it[Keys.ONBOARDING_DONE] ?: false }

    val savedEmail: Flow<String?> = preferencesFlow.map { it[Keys.SAVE_EMAIL] }

    suspend fun setAuthSession(token: String, userId: String){
        dataStore.edit { prefs ->
            prefs[Keys.AUTH_TOKEN] = token
            prefs[Keys.USER_ID] = userId
        }
    }

    suspend fun clearAuthSession() {
        dataStore.edit { prefs ->
            prefs.remove(Keys.AUTH_TOKEN)
            prefs.remove(Keys.USER_ID)
        }
    }

    suspend fun setDarkTheme(enabled: Boolean){
        dataStore.edit { it[Keys.DARK_THEME] = enabled }
    }

    suspend fun setOnBoardingDone() {
        dataStore.edit { it[Keys.ONBOARDING_DONE] = true }
    }

    suspend fun saveEmail(email:String){
        dataStore.edit { it[Keys.SAVE_EMAIL] = email }
    }
}
