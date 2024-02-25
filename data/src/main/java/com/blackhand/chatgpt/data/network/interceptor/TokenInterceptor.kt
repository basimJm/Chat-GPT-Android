package com.blackhand.chatgpt.data.network.interceptor

import android.content.SharedPreferences
import com.blackhand.chatgpt.core.constants.Constant.Companion.USER_TOKEN
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(private val sharedPreferences: SharedPreferences) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = sharedPreferences.getString(USER_TOKEN, "") ?: ""
        val newRequest = chain.request().newBuilder()
            .addHeader("authorization", "Bearer $token ")
            .build()
        return chain.proceed(newRequest)
    }
}
