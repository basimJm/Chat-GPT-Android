package com.blackhand.chatgpt.domin.repo

import com.blackhand.chatgpt.domin.model.UserInfoRemoteModel
import com.blackhand.chatgpt.domin.model.request.signin.LoginRequest
import retrofit2.Response

interface UserInfoRepository {
    suspend fun getUserInfo(): Response<UserInfoRemoteModel?>
    suspend fun loginUser(loginRequest: LoginRequest): Response<UserInfoRemoteModel?>
}