package com.example.doodlefrontend.repository

import android.util.Log
import com.example.doodlefrontend.configurations.RetrofitInstance
import com.example.doodlefrontend.model.backendResponse.CreateRoomResponse
import kotlinx.coroutines.runBlocking
import retrofit2.Response
import javax.inject.Inject

class CreateRoomRepo @Inject constructor(
    val retrofitInstance: RetrofitInstance
) {

     fun createRoom(roomName: String) : Response<CreateRoomResponse>{

        var retrofit = retrofitInstance.getInstance()

         val response : Response<CreateRoomResponse>

         runBlocking {
              response = retrofit.createRoom(
                 roomName = roomName
             )
         }

        return response


    }

}