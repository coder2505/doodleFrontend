package com.example.doodlefrontend.services

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doodlefrontend.backendNetwork.BackendApiService
import com.example.doodlefrontend.configurations.RetrofitInstance
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class UploadNamePost : ViewModel() {

    val sharedFlow = MutableSharedFlow<NameUpload>()

    fun uploadName(name : String) {

        val retService =
            RetrofitInstance.getInstance().create(BackendApiService::class.java)

        viewModelScope.launch {

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

}

sealed class NameUpload{
    class Success : NameUpload()
    class Error(val error: String) : NameUpload()
}

