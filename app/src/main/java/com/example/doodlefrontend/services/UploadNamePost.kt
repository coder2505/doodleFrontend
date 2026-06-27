package com.example.doodlefrontend.services

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doodlefrontend.backendNetwork.BackendApiService
import com.example.doodlefrontend.configurations.RetrofitInstance
import kotlinx.coroutines.launch

class UploadNamePost : ViewModel() {

    fun uploadName(name : String) {

        val retService =
            RetrofitInstance.getInstance().create(BackendApiService::class.java)

        viewModelScope.launch {

            val response = retService.createUser(name)
            if (response.isSuccessful) {

                Log.d("RESPONSE SUCCESS", "uploadName: ${response.body()}")

            } else {

                Log.e("error api", "uploadName: ${response.errorBody()}")

            }


        }

    }

}

