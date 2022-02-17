package com.sean.user_service.controller

import com.sean.user_service.service.AuthService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
@CrossOrigin
@RequestMapping("/auth")
class AuthController(
    private val authSrv: AuthService
) {

    @GetMapping("/refreshToken")
    fun refreshToken(request: HttpServletRequest, response: HttpServletResponse) {
        authSrv.refreshToken(request, response)
    }

}