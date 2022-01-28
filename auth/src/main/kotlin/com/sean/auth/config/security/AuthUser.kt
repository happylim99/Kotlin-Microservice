package com.sean.auth.config.security

import com.sean.auth.entity.User
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

class AuthUser {

    companion object {
        val principal =
            SecurityContextHolder.getContext().authentication.principal as String
//        val token =
//            (SecurityContextHolder.getContext().authentication.details as OAuth2AuthenticationDetails)
    }

}