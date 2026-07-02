package com.example.doodlefrontend.model.HTTPBody

import com.google.gson.annotations.SerializedName

data class RefreshEndpoint (

    @SerializedName("refreshToken")
    var refreshToken : String

)