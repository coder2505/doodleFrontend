package com.example.doodlefrontend.configurations

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{

        private const val BASE_URL =
            "https://harmony-hulkier-caridad.ngrok-free.dev"

        fun getInstance(): Retrofit {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
        }


    }
}