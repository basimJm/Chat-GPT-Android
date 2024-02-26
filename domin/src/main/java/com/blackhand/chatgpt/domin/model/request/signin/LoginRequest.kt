package com.blackhand.chatgpt.domin.model.request.signin

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequest(
    @Json(name = "email")
    var email: String? = null,
    @Json(name = "password")
    var password: String? = null
)
