package com.sean.user_service.ui.req

@kotlinx.serialization.Serializable
data class LoginReq(
    var email: String = "",
    var passwd: String = ""
)
