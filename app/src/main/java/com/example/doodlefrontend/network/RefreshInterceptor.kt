package com.example.doodlefrontend.network

import android.util.Log
import com.example.doodlefrontend.repository.RefreshTokenRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class RefreshInterceptor @Inject constructor(
    var refreshTokenRepository: RefreshTokenRepository
)
    : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()

        if (originalRequest.url.encodedPath.contains("/login/refresh")) {
            return chain.proceed(originalRequest)
        }

        val response = chain.proceed(originalRequest)

        if (response.code == 401 || response.code == 500) {

            val newAccessToken: String

            runBlocking {
                newAccessToken = refreshTokenRepository.refresh()
            }

            if (newAccessToken != "ERROR") {
                response.close()

                val newRequest = originalRequest.newBuilder()
                    .header("Authorization", "Bearer $newAccessToken")
                    .build()

                Log.d("Refresh Interceptor", "intercepted, new access Token $newAccessToken")

                return chain.proceed(newRequest)
            }
        }

        return response


    }
}