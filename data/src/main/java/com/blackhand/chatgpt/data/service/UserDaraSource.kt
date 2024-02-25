package com.blackhand.chatgpt.data.service

import com.blackhand.chatgpt.domin.model.UserInfoRemoteModel
import retrofit2.Response
import retrofit2.http.GET

interface UserDaraSource {

    @GET("validtoken")
    suspend fun getUserinfo(): Response<UserInfoRemoteModel?>
}