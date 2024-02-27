package com.blackhand.chatgpt.data.network.di

import com.blackhand.chatgpt.data.repo.UserInfoRepositoryImpl
import com.blackhand.chatgpt.data.service.UserDataSource
import com.blackhand.chatgpt.domin.repo.UserInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserInfoImpl(userDataSource: UserDataSource): UserInfoRepository {
        return UserInfoRepositoryImpl(userDataSource)
    }
}