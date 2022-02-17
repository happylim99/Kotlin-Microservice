package com.sean.user_service.ui.res

import com.sean.user_service.entity.Role

interface UserDto {
    val uid: String?
    val name: String?
    val username: String?
    val email: String?
    val roles: MutableList<Role>?
}