package com.example.doodlefrontend.repository

import android.util.Log
import com.example.doodlefrontend.configurations.RetrofitInstance
import retrofit2.Response
import javax.inject.Inject


class GetWidgetTextRepo @Inject constructor(
    val retrofitInstance: RetrofitInstance
) {

    suspend fun getText(): Response<String> {
        Log.d("from repository", "calling get widget text")
        return retrofitInstance.getInstance().getWidgetText()
    }

}