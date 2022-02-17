package com.sean.user_service.config.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.sean.user_service.entity.User
import com.sean.user_service.service.UserService
import com.sean.user_service.ui.req.LoginReq
import com.sean.user_service.util.AuthUtil
import com.sean.base.annotation.Slf4j
import com.sean.base.annotation.Slf4j.Companion.log
import com.sean.base.util.SpringContext
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Slf4j
class AuthenticationFilter(
    private val authManager: AuthenticationManager,
    private val mapper: ObjectMapper = SpringContext.getBean(ObjectMapper::class.java),
    private val authUtil: AuthUtil = SpringContext.getBean(AuthUtil::class.java)
): UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest?,
                                       response: HttpServletResponse?): Authentication {
        try {
            val cred = mapper.readValue(request?.inputStream, LoginReq::class.java)
            val authToken = UsernamePasswordAuthenticationToken(cred.email, cred.passwd)
            log.info("${cred.email} login")
            return authManager.authenticate(authToken)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val coreUser = authResult?.principal as org.springframework.security.core.userdetails.User
//        val access_token = authUtil.genAccessToken(coreUser.username,
//            request?.requestURL.toString(), coreUser.authorities.map { it.authority }.toList())
//        val refresh_token = authUtil.genRefreshToken(coreUser.username,
//            request?.requestURL.toString(), coreUser.authorities.map { it.authority }.toList())
        val user: User? = (SpringContext.getBean("userServiceImpl") as UserService)
            .findByEmail(coreUser.username)
        val accessToken = authUtil.genAccessToken(coreUser.username,
            request?.requestURL.toString(), coreUser.authorities.map { it.authority }.toList(),
            user
        )
        val refreshToken = authUtil.genRefreshToken(coreUser.username,
            request?.requestURL.toString(), coreUser.authorities.map { it.authority }.toList(),
            user
        )

        response?.addHeader(SecurityConst.HEADER_STRING, SecurityConst.TOKEN_PREFIX + accessToken)
        response?.contentType = "application/json"

        authUtil.authSuccessRes(response, accessToken, refreshToken)
    }

    fun InputStream.readTextAndClose(charset: Charset = Charsets.UTF_8): String {
        return this.bufferedReader(charset).use { it.readText() }
    }
}