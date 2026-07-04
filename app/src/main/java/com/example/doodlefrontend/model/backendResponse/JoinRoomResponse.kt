package com.example.doodlefrontend.model.backendResponse

import com.google.gson.annotations.SerializedName

data class JoinRoomResponse(

    @SerializedName("room_id")
    var roomId : String

)
