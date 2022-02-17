package com.sean.user_service.ui.res

import com.sean.user_service.entity.Role
import org.springframework.hateoas.RepresentationModel
//import sun.java2d.loops.DrawGlyphListAA

data class UserRes(
    var uid: String = "",
    var name: String = "",
    var username: String = "",
    var email: String? = null,
    var roles: MutableList<Role> = mutableListOf()
): RepresentationModel<UserRes>()