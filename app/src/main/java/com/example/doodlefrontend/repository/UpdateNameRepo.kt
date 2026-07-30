package com.example.doodlefrontend.repository

import com.example.doodlefrontend.configurations.RetrofitInstance
import retrofit2.Response
import java.util.UUID
import javax.inject.Inject

class UpdateNameRepo @Inject constructor(
    var retrofitInstance: RetrofitInstance
) {

    suspend fun uploadName(payload : String): Response<Unit> {

        return retrofitInstance.getInstance().updateText(payload = payload)

    }

}