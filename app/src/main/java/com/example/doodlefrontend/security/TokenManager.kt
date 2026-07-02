package com.example.doodlefrontend.security

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "token_prefs")

@Singleton
class TokenManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val cryptoManager = CryptoManager()

    companion object {
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
        private val USER_ID_KEY = stringPreferencesKey("user_id")
    }

    suspend fun saveTokens(accessToken: String, refreshToken: String, userId: String) {
        val encryptedAccess = cryptoManager.encryptString(accessToken)
        val encryptedRefresh = cryptoManager.encryptString(refreshToken)
        val encryptedUserId = cryptoManager.encryptString(userId)
        
        context.dataStore.edit { prefs ->
            prefs[ACCESS_TOKEN_KEY] = encryptedAccess
            prefs[REFRESH_TOKEN_KEY] = encryptedRefresh
            prefs[USER_ID_KEY] = encryptedUserId
        }
    }

    val accessTokenFlow: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[ACCESS_TOKEN_KEY]?.let { cryptoManager.decryptString(it) }
    }

    val refreshTokenFlow: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[REFRESH_TOKEN_KEY]?.let { cryptoManager.decryptString(it) }
    }
    
    val userIdFlow: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[USER_ID_KEY]?.let { cryptoManager.decryptString(it) }
    }

    fun getAccessToken(): String? {
        return runBlocking { accessTokenFlow.first() }
    }

    fun getRefreshToken(): String? {
        return runBlocking { refreshTokenFlow.first() }
    }
}
