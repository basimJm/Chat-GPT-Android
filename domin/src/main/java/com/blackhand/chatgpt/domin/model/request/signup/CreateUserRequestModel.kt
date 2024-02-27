package com.blackhand.chatgpt.domin.model.request.signup

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateUserRequestModel(
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "email")
    var email: String? = null,
    @Json(name = "phone")
    var phone: String? = null,
    @Json(name = "password")
    var password: String? = null,
    @Json(name = "passwordConfirm")
    var passwordConfirmation: String? = null,
)
