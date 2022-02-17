package com.sean.user_service.service.impl

import com.sean.user_service.entity.Role
import com.sean.user_service.repo.RoleRepo
import com.sean.user_service.service.RoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RoleServiceImpl @Autowired constructor(
    private val rRepo: RoleRepo
): RoleService {

    override fun saveOne(role: Role): Role {
        return rRepo.save(role)
    }
}