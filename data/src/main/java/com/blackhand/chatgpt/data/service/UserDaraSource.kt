package com.blackhand.chatgpt.data.service

import com.blackhand.chatgpt.domin.model.UserInfoRemoteModel
import com.blackhand.chatgpt.domin.model.request.signin.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserDaraSource {

    @GET("validtoken")
    suspend fun getUserinfo(): Response<UserInfoRemoteModel?>

    @POST("signin")
    suspend fun loginInUser(@Body loginRequest: LoginRequest): Response<UserInfoRemoteModel?>
}