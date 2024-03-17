package com.blackhand.chatgpt.domin.model.sessions

import com.blackhand.chatgpt.domin.model.Session
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SessionsHistory(
    @Json(name = "sessions")
    val sessions: Session? = null
)