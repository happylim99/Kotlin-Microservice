package com.sean.auth.ui.res

import com.sean.auth.entity.Role

interface UserDto {
    val uid: String?
    val name: String?
    val username: String?
    val email: String?
    val roles: MutableList<Role>?
}