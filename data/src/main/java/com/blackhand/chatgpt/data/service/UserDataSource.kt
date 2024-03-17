package com.blackhand.chatgpt.data.service

import com.blackhand.chatgpt.domin.model.UserInfoRemoteModel
import com.blackhand.chatgpt.domin.model.request.signin.LoginRequestModel
import com.blackhand.chatgpt.domin.model.request.signup.CreateUserRequestModel
import com.blackhand.chatgpt.domin.model.sessions.SessionsHistory
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserDataSource {

    @GET("text-correction/validtoken")
    suspend fun getUserinfo(): Response<UserInfoRemoteModel?>

    @POST("text-correction/signin")
    suspend fun loginUser(@Body loginRequest: LoginRequestModel): Response<UserInfoRemoteModel?>

    @POST("text-correction/signup")
    suspend fun createUser(@Body createUserRequest: CreateUserRequestModel): Response<UserInfoRemoteModel?>

    @GET("chat-history/{userID}/{sessionID}")
    suspend fun getUserChatHistory(
        @Path("userID") userID: String, @Path("sessionID") sessionID: String
    ): Response<SessionsHistory?>
}