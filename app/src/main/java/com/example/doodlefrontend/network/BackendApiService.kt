package com.example.doodlefrontend.network

import com.example.doodlefrontend.model.HTTPBody.RefreshEndpoint
import com.example.doodlefrontend.model.backendResponse.CreateRoomResponse
import com.example.doodlefrontend.model.backendResponse.CreateUserResponse
import com.example.doodlefrontend.model.backendResponse.JoinRoomResponse
import com.example.doodlefrontend.model.backendResponse.RefreshTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface BackendApiService {

    @POST("login/user/{username}/{fcmToken}")
    suspend fun createUser(
        @Path("username") username: String,
        @Path("fcmToken") fcmToken : String
    ): Response<CreateUserResponse>


    @POST("/create-room/{roomName}")
    suspend fun createRoom(
        @Path("roomName") roomName : String
    ): Response<CreateRoomResponse>


    @POST("/join-room/{roomId}")
    suspend fun joinRoom(
        @Path("roomId") roomId : String
    ) : Response<JoinRoomResponse>

    @POST("/refresh")
    suspend fun refresh(
        @Body refreshEndpoint: RefreshEndpoint
    ): Response<RefreshTokenResponse>

}