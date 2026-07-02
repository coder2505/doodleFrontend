package com.example.doodlefrontend.model.backendResponse

import com.google.gson.annotations.SerializedName

data class RefreshTokenResponse(

    @SerializedName("Access Token")
    var accessToken : String

)
