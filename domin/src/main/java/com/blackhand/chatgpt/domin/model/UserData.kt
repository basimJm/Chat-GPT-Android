package com.blackhand.chatgpt.domin.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserData(
    @Json(name = "_id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "email") val email: String,
    @Json(name = "phone") val phone: String,
    @Json(name = "profileImage") val profileImage: String,
    @Json(name = "password") val password: String,
    @Json(name = "sessions") val sessions: List<Session>,
    @Json(name = "stayLogin") val stayLogin: Boolean,
    @Json(name = "createdAt") val createdAt: String,
    @Json(name = "updatedAt") val updatedAt: String,
    @Json(name = "__v") val v: Int
)