package com.example.doodlefrontend.model.backendResponse

import com.google.gson.annotations.SerializedName

data class CreateRoomResponse(

    @SerializedName("room_id")
    var roomId : Long
)
