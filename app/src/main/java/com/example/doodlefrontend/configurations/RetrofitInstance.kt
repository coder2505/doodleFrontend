package com.example.doodlefrontend.configurations

import com.example.doodlefrontend.backendNetwork.BackendApiService
import com.example.doodlefrontend.backendNetwork.HeaderInterceptor
import com.example.doodlefrontend.backendNetwork.RefreshInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitInstance @Inject constructor(
    var headerInterceptor: HeaderInterceptor,
    var refreshInterceptor: RefreshInterceptor
){

    private val BASE_URL =
        "https://harmony-hulkier-caridad.ngrok-free.dev"

    fun getInstance(): BackendApiService {

        val interceptors = OkHttpClient.Builder().addInterceptor(headerInterceptor).addInterceptor(refreshInterceptor).build()


        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(interceptors)
            .build().create(BackendApiService::class.java)
    }


}