package com.blackhand.chatgpt.domin.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@JsonClass(generateAdapter = true)

data class Session(
    @Json(name = "_id") val id: String? = null,
    @Json(name = "sessionName") val sessionName: String? = null,
    @Json(name = "chatHistory") val chatHistory: List<ChatHistory>? = null,
    @Json(name = "createdAt") val createdAt: String? = null,
    @Json(name = "updatedAt") val updatedAt: String? = null,
    @Json(name = "__v") val v: Int? = null
) : Serializable