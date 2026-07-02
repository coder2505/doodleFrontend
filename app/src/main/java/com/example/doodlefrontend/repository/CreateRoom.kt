package com.example.doodlefrontend.repository

import android.util.Log
import com.example.doodlefrontend.configurations.RetrofitInstance
import com.example.doodlefrontend.security.TokenManager
import javax.inject.Inject
import kotlin.math.log

class CreateRoomRepo @Inject constructor(
    val tokenManager: TokenManager,
    val retrofitInstance: RetrofitInstance
) {

    suspend fun createRoom(roomName: String){

        var retrofit = retrofitInstance.getInstance()

        val response = retrofit.createRoom(
            roomName = roomName
        )

        if(response.isSuccessful){
            Log.d("CREATE ROOM", "createRoom: ${response.body()?.roomId}")

        }else{

            Log.e("Error in room creation", "createRoom: ${response.errorBody().toString()}" )
        }


    }

}