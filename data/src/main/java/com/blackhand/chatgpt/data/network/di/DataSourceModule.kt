package com.blackhand.chatgpt.data.network.di

import com.blackhand.chatgpt.data.service.UserDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideDataSource(retrofit: Retrofit): UserDataSource {
        return retrofit.create(UserDataSource::class.java)
    }
}