package com.example.doodlefrontend.repository

import android.util.Log
import com.example.doodlefrontend.configurations.RetrofitInstance
import com.example.doodlefrontend.model.HTTPBody.RefreshEndpoint
import com.example.doodlefrontend.security.TokenManager
import dagger.Lazy
import javax.inject.Inject

class RefreshTokenRepository @Inject constructor(
    var tokenManager: TokenManager,
    var retrofitInstance: Lazy<RetrofitInstance>
) {

    suspend fun refresh() : String {
        val refreshToken = tokenManager.getRefreshToken() ?: return "ERROR"
        val response = retrofitInstance.get().getInstance().refresh(
            RefreshEndpoint(
                refreshToken = refreshToken
            )
        )

        Log.d("/refresh", response.toString())
        val newAccessToken = response.body()?.accessToken
        if (newAccessToken != null) {
            tokenManager.saveAccessToken(newAccessToken)
            return newAccessToken
        }
        return "ERROR"
    }

}