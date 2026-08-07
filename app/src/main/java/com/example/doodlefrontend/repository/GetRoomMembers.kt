package com.example.doodlefrontend.repository

import com.example.doodlefrontend.configurations.RetrofitInstance
import retrofit2.Response
import javax.inject.Inject

class GetRoomMembers @Inject constructor(
    val retrofitInstance: RetrofitInstance
) {

    suspend fun getRoomMembers() : Response<List<String>>{
        return retrofitInstance.getInstance().getRoomMembers()
    }


}