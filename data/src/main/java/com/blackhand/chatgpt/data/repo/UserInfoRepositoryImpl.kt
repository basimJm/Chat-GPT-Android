package com.blackhand.chatgpt.data.repo

import com.blackhand.chatgpt.data.service.UserDataSource
import com.blackhand.chatgpt.domin.model.UserInfoRemoteModel
import com.blackhand.chatgpt.domin.model.request.signin.LoginRequestModel
import com.blackhand.chatgpt.domin.model.request.signup.CreateUserRequestModel
import com.blackhand.chatgpt.domin.repo.UserInfoRepository
import retrofit2.Response
import javax.inject.Inject

class UserInfoRepositoryImpl @Inject constructor(private val userDataSource: UserDataSource) :
    UserInfoRepository {
    override suspend fun getUserInfo(): Response<UserInfoRemoteModel?> {
        return userDataSource.getUserinfo()
    }

    override suspend fun loginUser(loginRequest: LoginRequestModel): Response<UserInfoRemoteModel?> {
        return userDataSource.loginUser(loginRequest)
    }

    override suspend fun sighUpUser(createUserRequest: CreateUserRequestModel): Response<UserInfoRemoteModel?> {
        return userDataSource.createUser(createUserRequest)
    }
}