package com.example.doodlefrontend.network

import com.example.doodlefrontend.security.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor(
    val tokenManager: TokenManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val currentRequest = chain.request().newBuilder()
        currentRequest.addHeader(
            "Authorization", "Bearer ".plus(
                tokenManager.getAccessToken()
            )
        )

        val newRequest = currentRequest.build()
        return chain.proceed(newRequest)

    }

}