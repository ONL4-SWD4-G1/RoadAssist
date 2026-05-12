package com.example.roadassist.auth.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.sessionDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "roadassist_session",
)

class SessionStore(private val context: Context) {

    val isLoggedIn: Flow<Boolean> = context.sessionDataStore.data.map { prefs ->
        prefs[KEY_LOGGED_IN] == true
    }

    val userEmail: Flow<String?> = context.sessionDataStore.data.map { prefs ->
        prefs[KEY_EMAIL]
    }

    suspend fun saveSession(email: String) {
        context.sessionDataStore.edit { prefs ->
            prefs[KEY_LOGGED_IN] = true
            prefs[KEY_EMAIL] = email
        }
    }

    suspend fun clearSession() {
        context.sessionDataStore.edit { prefs ->
            prefs.remove(KEY_LOGGED_IN)
            prefs.remove(KEY_EMAIL)
        }
    }

    private companion object {
        val KEY_LOGGED_IN = booleanPreferencesKey("logged_in")
        val KEY_EMAIL = stringPreferencesKey("email")
    }
}
