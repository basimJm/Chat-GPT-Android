package com.blackhand.chatgpt.data.network.interceptor

import android.content.SharedPreferences
import com.blackhand.chatgpt.core.constants.Constant.Companion.AUTHORIZATION
import com.blackhand.chatgpt.core.constants.Constant.Companion.BEARER
import com.blackhand.chatgpt.core.constants.Constant.Companion.DEFAULT_VALUE
import com.blackhand.chatgpt.core.constants.Constant.Companion.USER_TOKEN
import com.blackhand.chatgpt.core.utils.SharedPref
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(private val sharedPreferences: SharedPreferences) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = sharedPreferences.getString(USER_TOKEN, DEFAULT_VALUE)
        val newRequest = chain.request().newBuilder()
            .addHeader(AUTHORIZATION, "$BEARER $token ")
            .build()
        return chain.proceed(newRequest)
    }
}
