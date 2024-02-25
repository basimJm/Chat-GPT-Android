package com.blackhand.chatgpt.domin.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserData(
    @Json(name = "_id") val id: String? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "email") val email: String? = null,
    @Json(name = "phone") val phone: String? = null,
    @Json(name = "profileImage") val profileImage: String? = null,
    @Json(name = "password") val password: String? = null,
    @Json(name = "sessions") val sessions: List<Session>? = null,
    @Json(name = "stayLogin") val stayLogin: Boolean? = null,
    @Json(name = "createdAt") val createdAt: String? = null,
    @Json(name = "updatedAt") val updatedAt: String? = null,
    @Json(name = "__v") val v: Int? = null
)