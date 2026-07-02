package com.example.doodlefrontend.configurations

import com.example.doodlefrontend.backendNetwork.BackendApiService
import com.example.doodlefrontend.security.TokenManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitInstance @Inject constructor(
    private val tokenManager: TokenManager
) {

        private val BASE_URL =
            "https://harmony-hulkier-caridad.ngrok-free.dev"

        fun getInstance(): BackendApiService {

            val client = OkHttpClient.Builder().addInterceptor { chain ->
                val currentRequest = chain.request().newBuilder()
                currentRequest.addHeader(
                    "Authorization", "Bearer ".plus(
                        tokenManager.getAccessToken()
                    )
                )

                val newRequest = currentRequest.build()
                chain.proceed(newRequest)

            }.build()


            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build().create(BackendApiService::class.java)
        }


}