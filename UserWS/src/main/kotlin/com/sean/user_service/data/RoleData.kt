package com.sean.user_service.data

import com.sean.user_service.entity.Role
import com.sean.user_service.service.RoleService
import com.sean.user_service.service.impl.RoleServiceImpl
import com.sean.base.util.SpringContext
import org.springframework.boot.CommandLineRunner

//@Component
//@Order(1)
class RoleData(
    private val roleSrv: RoleService = SpringContext.getBean(RoleServiceImpl::class.java)
): CommandLineRunner {

    override fun run(vararg args: String?) {
        roleSrv.saveOne(Role(Role.RoleName.root.name))
        roleSrv.saveOne(Role(Role.RoleName.admin.name))
        roleSrv.saveOne(Role(Role.RoleName.superuser.name))
        roleSrv.saveOne(Role(Role.RoleName.user.name))
    }
}