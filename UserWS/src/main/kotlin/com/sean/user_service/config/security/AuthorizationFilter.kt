package com.sean.user_service.config.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.sean.user_service.util.AuthUtil
import com.sean.base.annotation.Slf4j
import com.sean.base.annotation.Slf4j.Companion.log
import com.sean.base.util.SpringContext
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Slf4j
class AuthorizationFilter(
    private val authManager: AuthenticationManager,
    private val mapper: ObjectMapper = SpringContext.getBean(ObjectMapper::class.java),
    private val authUtil: AuthUtil = SpringContext.getBean(AuthUtil::class.java)
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        if(request.servletPath.equals("/user/login")
            || request.servletPath.equals("/user/register")
            || request.servletPath.equals("/user/refreshToken")) {
            filterChain.doFilter(request, response)
        } else {
            val authHeader: String? = request.getHeader(SecurityConst.HEADER_STRING)
            if(authHeader != null && authHeader.startsWith(SecurityConst.TOKEN_PREFIX)) {
                try {
                    val decodeJwt = authUtil.decodeJwt(request, response)
                    val username = decodeJwt.subject
                    val roles = decodeJwt.getClaim("roles").asArray(String::class.java)
                    val userStr = decodeJwt.getClaim("user").asString()
                    val user = mapper.readValue(userStr, com.sean.user_service.entity.User::class.java)

//                    println(user.roles?.elementAt(0)?.name)
                    val authorities = roles.map {
                        SimpleGrantedAuthority(it)
                    }.toList()
                    val authToken = UsernamePasswordAuthenticationToken(username, user, authorities)
                    SecurityContextHolder.getContext().authentication = authToken
//                    SecurityContextHolder.getContext().authentication
                    filterChain.doFilter(request, response)
                } catch (e: Exception) {
                    log.error("AuthorizationFilter error $e")
                    authUtil.authFailRes(response, e)
//                    response.sendError(FORBIDDEN.absoluteValue)
                }

            } else {
                filterChain.doFilter(request, response)
            }
        }
    }
}