package com.blackhand.chatgpt.data.network.di

import com.blackhand.chatgpt.data.service.UserDaraSource
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
    fun provideDataSource(retrofit: Retrofit): UserDaraSource {
        return retrofit.create(UserDaraSource::class.java)
    }
}