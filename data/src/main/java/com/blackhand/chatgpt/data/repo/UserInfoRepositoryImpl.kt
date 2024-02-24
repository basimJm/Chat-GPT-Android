package com.blackhand.chatgpt.data.repo

import com.blackhand.chatgpt.data.service.UserDaraSource
import com.blackhand.chatgpt.domin.model.UserInfoRemoteModel
import com.blackhand.chatgpt.domin.repo.UserInfoRepository
import javax.inject.Inject

class UserInfoRepositoryImpl @Inject constructor(private val userDaraSource: UserDaraSource):UserInfoRepository {
    override suspend fun getUserInfo(): UserInfoRemoteModel = userDaraSource.getUserinfo()
}