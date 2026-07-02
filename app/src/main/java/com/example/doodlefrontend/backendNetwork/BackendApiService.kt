package com.example.doodlefrontend.backendNetwork

import com.example.doodlefrontend.model.HTTPBody.RefreshEndpoint
import com.example.doodlefrontend.model.backendResponse.CreateRoomResponse
import com.example.doodlefrontend.model.backendResponse.CreateUserResponse
import com.example.doodlefrontend.model.backendResponse.RefreshTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface BackendApiService {

    @POST("login/user/{username}")
    suspend fun createUser(
        @Path("username") username: String
    ): Response<CreateUserResponse>


    @POST("/create-room/{roomName}")
    suspend fun createRoom(
        @Path("room-name") roomName : String
    ): Response<CreateRoomResponse>

    @POST("/refresh")
    suspend fun refresh(
        @Body refreshEndpoint: RefreshEndpoint
    ): Response<RefreshTokenResponse>


}