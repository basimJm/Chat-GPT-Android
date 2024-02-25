package com.blackhand.chatgpt.domin.repo

import com.blackhand.chatgpt.domin.model.UserInfoRemoteModel
import retrofit2.Response

interface UserInfoRepository {
    suspend fun getUserInfo(): Response<UserInfoRemoteModel?>
}