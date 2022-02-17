package com.sean.user_service.repo

import com.sean.user_service.entity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepo: JpaRepository<Role, Long> {

    fun findByName(name: String): Role
}