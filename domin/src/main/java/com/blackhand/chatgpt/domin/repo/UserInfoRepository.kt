package com.blackhand.chatgpt.domin.repo

import com.blackhand.chatgpt.domin.model.UserInfoRemoteModel

interface UserInfoRepository {
    suspend fun getUserInfo(): UserInfoRemoteModel
}