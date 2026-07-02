package com.example.doodlefrontend.repository

import com.example.doodlefrontend.configurations.RetrofitInstance
import com.example.doodlefrontend.model.HTTPBody.RefreshEndpoint
import com.example.doodlefrontend.security.TokenManager
import dagger.Lazy
import javax.inject.Inject

class RefreshTokenRepository @Inject constructor(
    var tokenManager: TokenManager,
    var retrofitInstance: Lazy<RetrofitInstance>
) {

    suspend fun refresh() : String{

        val response = retrofitInstance.get().getInstance().refresh(
            RefreshEndpoint(
                refreshToken = tokenManager.getRefreshToken() ?: "ERROR"
            )
        )

        return response.body()?.accessToken ?: "ERROR"

    }

}