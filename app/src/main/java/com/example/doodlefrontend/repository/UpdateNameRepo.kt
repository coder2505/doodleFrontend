package com.example.doodlefrontend.repository

import com.example.doodlefrontend.configurations.RetrofitInstance
import com.example.doodlefrontend.model.RequestBodies.UpdateText
import retrofit2.Response
import java.util.UUID
import javax.inject.Inject

class UpdateNameRepo @Inject constructor(
    var retrofitInstance: RetrofitInstance
) {

    suspend fun uploadName(payload : String, font : String, background : String): Response<Unit> {

        return retrofitInstance.getInstance().updateText(UpdateText(payload=payload, font = font, color = background))

    }

}