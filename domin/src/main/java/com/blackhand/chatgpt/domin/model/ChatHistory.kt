package com.blackhand.chatgpt.domin.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChatHistory(
    @Json(name = "_id") val id: String? = null,
    @Json(name = "userMessage") val userMessage: String? = null,
    @Json(name = "responseMessage") val responseMessage: String? = null,
    @Json(name = "__v") val v: Int? = null
)