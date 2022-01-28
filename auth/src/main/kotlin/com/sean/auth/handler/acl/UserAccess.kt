package com.sean.auth.handler.acl

import com.sean.auth.config.security.AuthUser
import org.springframework.stereotype.Component

@Component
class UserAccess {

    fun test(uid: String, aaa: String): Boolean {
        println(aaa)
        println(uid)
        println(AuthUser.principal)
        return true
    }
}