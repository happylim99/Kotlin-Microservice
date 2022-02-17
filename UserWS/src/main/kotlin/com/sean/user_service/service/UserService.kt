package com.sean.user_service.service

import com.sean.user_service.entity.User
import com.sean.user_service.ui.req.UserCrtReq
import com.sean.user_service.ui.req.UserUptReq
import com.sean.user_service.ui.res.UserRes
import com.sean.base.service.Crud
import org.springframework.security.core.userdetails.UserDetailsService
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

interface UserService: UserDetailsService, Crud<UserRes, UserCrtReq, UserUptReq> {

    fun showPag(page: Int, limit: Int): List<UserRes>
    fun addRole(userId: String, role: String)
    fun findByEmail(email: String): User?
    fun findByEmailReturnUserRes(email: String): UserRes?
    fun refreshToken(request: HttpServletRequest, response: HttpServletResponse)
}