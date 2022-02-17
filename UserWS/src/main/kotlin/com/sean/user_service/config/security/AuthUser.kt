package com.sean.user_service.config.security

import org.springframework.security.core.context.SecurityContextHolder

class AuthUser {

    companion object {
        val principal =
            SecurityContextHolder.getContext().authentication.principal as String
//        val token =
//            (SecurityContextHolder.getContext().authentication.details as OAuth2AuthenticationDetails)
    }

}