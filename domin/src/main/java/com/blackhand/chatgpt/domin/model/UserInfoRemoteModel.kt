package com.blackhand.chatgpt.domin.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@JsonClass(generateAdapter = true)

data class UserInfoRemoteModel(
    @Json(name = "status") val status: Int? = null,
    @Json(name = "user_data") val userData: UserData? = null,
    @Json(name = "token") val token: String? = null
) : Serializable