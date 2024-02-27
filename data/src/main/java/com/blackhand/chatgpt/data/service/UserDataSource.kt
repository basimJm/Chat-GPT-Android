package com.blackhand.chatgpt.data.service

import com.blackhand.chatgpt.domin.model.UserInfoRemoteModel
import com.blackhand.chatgpt.domin.model.request.signin.LoginRequestModel
import com.blackhand.chatgpt.domin.model.request.signup.CreateUserRequestModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserDataSource {

    @GET("validtoken")
    suspend fun getUserinfo(): Response<UserInfoRemoteModel?>

    @POST("signin")
    suspend fun loginUser(@Body loginRequest: LoginRequestModel): Response<UserInfoRemoteModel?>

    @POST("signup")
    suspend fun createUser(@Body createUserRequest: CreateUserRequestModel): Response<UserInfoRemoteModel?>
}