package com.sean.user_service.config.security

object SecurityConst {

    const val TOKEN_EXPIRATION_TIME = 86400000 * 1 // 1 days
    const val REFRESH_EXPIRATION_TIME = 86400000 * 2 // 2 days
    const val EMAIL_TOKEN_EXPIRATION_TIME = 86400000 * 1 // 1 days
//    const val TOKEN_EXPIRATION_TIME = 60000 * 1 // 1 minute
//    const val REFRESH_EXPIRATION_TIME = 60000 * 5 // 5 minutes
    const val TOKEN_PREFIX = "Bearer "
    const val HEADER_STRING = "Authorization"
    const val SIGN_UP_URL = "/user/saveOne"

//    fun getSecretToken(): String? {
//        val appProp: AppProperty = SpringContext
//            .getBean("AppProperty") as AppProperty
//        return appProp.getSecretToken()
//    }

//    fun getSecretToken() =
//        (SpringContext.getBean("AppProperty") as AppProperty).getSecretToken()

}