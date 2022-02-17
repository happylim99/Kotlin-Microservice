package com.sean.user_service.service

import com.sean.user_service.entity.Role

interface RoleService {

    fun saveOne(role: Role): Role
}