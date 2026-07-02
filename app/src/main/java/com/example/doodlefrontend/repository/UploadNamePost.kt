package com.example.doodlefrontend.repository

import android.util.Log
import com.example.doodlefrontend.backendNetwork.BackendApiService
import com.example.doodlefrontend.configurations.RetrofitInstance
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

class UploadNamePost @Inject constructor() {

    val sharedFlow = MutableSharedFlow<NameUpload>()

    suspend fun uploadName(name : String) {

        val retService =
            RetrofitInstance.getInstance().create(BackendApiService::class.java)

            val response = retService.createUser(name)
            if (response.isSuccessful) {

                Log.d("RESPONSE SUCCESS", "uploadName: ${response.body()}")
                sharedFlow.emit(NameUpload.Success())

            } else {

                Log.e("error api", "uploadName: ${response.errorBody()}")
                sharedFlow.emit(NameUpload.Error("Trouble Uploading"))

            }

    }

}

sealed class NameUpload{
    class Success : NameUpload()
    class Error(val error: String) : NameUpload()
}

