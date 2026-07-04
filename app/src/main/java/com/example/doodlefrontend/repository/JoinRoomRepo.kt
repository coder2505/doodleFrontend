package com.example.doodlefrontend.repository

import android.util.Log
import com.example.doodlefrontend.configurations.RetrofitInstance
import com.example.doodlefrontend.model.backendResponse.JoinRoomResponse
import kotlinx.coroutines.runBlocking
import retrofit2.Response
import javax.inject.Inject

class JoinRoomRepo @Inject constructor(
    var retrofitInstance: RetrofitInstance
) {

     suspend fun join(roomId : String): Response<JoinRoomResponse>{

             Log.d("inside join room repo", "join: $roomId")
             return retrofitInstance.getInstance().joinRoom(roomId)


    }

}