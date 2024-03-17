package com.blackhand.chatgpt.domin.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class ChatHistory(
    @Json(name = "_id") val id: String? = null,
    @Json(name = "userMessage") val userMessage: String? = null,
    @Json(name = "responseMessage") val responseMessage: String? = null,
    @Json(name = "__v") val v: Int? = null
) : Serializable