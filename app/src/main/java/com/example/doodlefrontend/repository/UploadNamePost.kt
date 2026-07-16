package com.example.doodlefrontend.repository

import android.util.Log
import com.example.doodlefrontend.configurations.RetrofitInstance
import com.example.doodlefrontend.security.TokenManager
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
class UploadNamePost @Inject constructor(
    private val tokenManager: TokenManager,
    private val retrofitInstance: RetrofitInstance
) {

    val sharedFlow = MutableSharedFlow<NameUpload>()

    suspend fun uploadName(name : String, fcmToken : String) {

        val retService = retrofitInstance.getInstance()

            val response = retService.createUser(name, fcmToken = fcmToken)
            if (response.isSuccessful) {

                Log.d("RESPONSE SUCCESS", "uploadName: ${response.body()}")
                response.body()?.let {
                    tokenManager.saveTokens(it.accessToken, it.refreshToken, it.userId)
                }
                sharedFlow.emit(NameUpload.Success())

            } else {

                Log.e("error api", "uploadName: ${response.errorBody()?.string()}")
                sharedFlow.emit(NameUpload.Error("Trouble Uploading"))

            }

    }

}

sealed class NameUpload{
    class Success : NameUpload()
    class Error(val error: String) : NameUpload()
}

