package com.example.doodlefrontend.model.backendResponse

import com.google.gson.annotations.SerializedName

data class CreateUserResponse(

    @SerializedName("name")
    val name : String,

    @SerializedName("user_id")
    val userId : String,

    @SerializedName("Access Token")
    val accessToken : String,

    @SerializedName("Refresh Token")
    val refreshToken : String

)
