package com.sean.auth.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.fasterxml.jackson.databind.ObjectMapper
import com.sean.auth.config.security.SecurityConst
import com.sean.auth.entity.Role
import com.sean.base.ext.asMap
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthUtil(
    private val mapper: ObjectMapper,
    private val env: Environment
) {

    fun decodeJwt(request: HttpServletRequest, response: HttpServletResponse): DecodedJWT {
        val authHeader = request.getHeader(SecurityConst.HEADER_STRING)
        val token = authHeader.replace(SecurityConst.TOKEN_PREFIX, "")
        val jwtVerifier = JWT.require(algorithm()).build()
        return jwtVerifier.verify(token)
    }

    fun genAccessToken(username: String?, issuer: String, roles: List<String>?,
                       user: com.sean.auth.entity.User?): String {
//        val mapp: Map<String, Any?>? = user?.asMap()
        val role = Role(Role.RoleName.user.name)
        user?.roles?.add(role)
        val userStr = mapper.writeValueAsString(user)
        println(env.getProperty("secret.token"))
        return JWT.create()
            .withSubject(username)
            .withExpiresAt(Date(System.currentTimeMillis() + SecurityConst.TOKEN_EXPIRATION_TIME))
            .withIssuer(issuer)
            .withClaim("roles", roles)
            .withClaim("user", userStr)
            .sign(algorithm())
    }

    private fun makeObjMap(user: com.sean.auth.entity.User?): Map<String, Any?>? {
        user?.apply {
            addresses == null
            roles == null
        }
        return user?.asMap()
    }

    fun genEmailToken(): String {
        return JWT.create()
            .withExpiresAt(Date(System.currentTimeMillis() +
                    SecurityConst.EMAIL_TOKEN_EXPIRATION_TIME))
            .sign(algorithm())
    }

    fun genRefreshToken(username: String?, issuer: String, roles: List<String>?,
                        user: com.sean.auth.entity.User?): String {
        return JWT.create()
            .withSubject(username)
            .withExpiresAt(Date(System.currentTimeMillis() + SecurityConst.REFRESH_EXPIRATION_TIME))
            .withIssuer(issuer)
            .withClaim("roles", roles)
//            .withClaim("objMap", makeObjMap(user))
            .sign(algorithm())
    }

    fun authSuccessRes(response: HttpServletResponse?, accessToken: String, refreshToken: String) {
        response?.let {
            var res = mutableMapOf<String, String>()
            res["access_token"] = accessToken
            res["refresh_token"] = refreshToken
            mapper.writeValue(response.outputStream, res)
        }
    }

    fun authFailRes(response: HttpServletResponse, e: Exception) {
        response.setHeader("error", e.message)
        response.status = HttpStatus.FORBIDDEN.value()
        response?.let {
            var res = mutableMapOf<String, String>()
            res["error_message"] = e.message.toString()
            mapper.writeValue(response.outputStream, res)
        }
    }

    fun algorithm() = Algorithm.HMAC512(env.getProperty("secret.token"))

}