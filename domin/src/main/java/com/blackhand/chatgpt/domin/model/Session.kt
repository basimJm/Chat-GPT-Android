package com.blackhand.chatgpt.domin.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Session(
    @Json(name = "_id") val id: String,
    @Json(name = "sessionName") val sessionName: String,
    @Json(name = "chatHistory") val chatHistory: List<ChatHistory>,
    @Json(name = "createdAt") val createdAt: String,
    @Json(name = "updatedAt") val updatedAt: String,
    @Json(name = "__v") val v: Int
)