package com.blackhand.chatgpt.domin.repo

import com.blackhand.chatgpt.domin.model.Session
import com.blackhand.chatgpt.domin.model.UserInfoRemoteModel
import com.blackhand.chatgpt.domin.model.request.signin.LoginRequestModel
import com.blackhand.chatgpt.domin.model.request.signup.CreateUserRequestModel
import com.blackhand.chatgpt.domin.model.sessions.SessionsHistory
import retrofit2.Response

interface UserInfoRepository {
    suspend fun getUserInfo(): Response<UserInfoRemoteModel?>
    suspend fun loginUser(loginRequest: LoginRequestModel): Response<UserInfoRemoteModel?>
    suspend fun sighUpUser(createUserRequest: CreateUserRequestModel): Response<UserInfoRemoteModel?>
    suspend fun getUserChatHistory(userID:String,sessionID:String):Response<SessionsHistory?>
}