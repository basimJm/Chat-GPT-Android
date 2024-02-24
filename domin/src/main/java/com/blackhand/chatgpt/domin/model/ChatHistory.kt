package com.blackhand.chatgpt.domin.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChatHistory(
    @Json(name = "_id") val id: String,
    @Json(name = "userMessage") val userMessage: String,
    @Json(name = "responseMessage") val responseMessage: String,
    @Json(name = "__v") val v: Int
)