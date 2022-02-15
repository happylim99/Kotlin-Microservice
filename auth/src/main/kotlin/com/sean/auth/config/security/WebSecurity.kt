package com.sean.auth.config.security

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.RequestMatcher
import javax.servlet.http.HttpServletRequest


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurity(
    private val userDetailsService: UserDetailsService,
    private val bcrypt: BCryptPasswordEncoder
): WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsService)
            ?.passwordEncoder(bcrypt)
    }

    override fun configure(http: HttpSecurity) {
//        http.authorizeRequests()
//            .antMatchers("/**").hasIpAddress("192.168.0.7")
        http.csrf().disable().authorizeRequests()
            .requestMatchers(checkPort(9005)).authenticated()
            .antMatchers("/**")
//            .hasIpAddress("192.168.0.7")
//            .access("hasIpAddress(\"127.0.0.1\") or hasIpAddress(\"::1\")")
            .access("hasIpAddress(\"127.0.0.1\") or hasIpAddress(\"::1\")")
//            .permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilter(getAuthenticationFilter())
            .addFilterBefore(AuthorizationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter::class.java)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    @Throws(Exception::class)
    fun getAuthenticationFilter(): AuthenticationFilter? {
        val filter = AuthenticationFilter(authenticationManager())
        filter.setFilterProcessesUrl("/user/login")
        return filter
    }

    @Throws(Exception::class)
    override fun configure(web: WebSecurity?) {
        web?.let {
            it.ignoring()
                .antMatchers(HttpMethod.POST,"/user/register")
                .antMatchers(HttpMethod.GET,"/user/refreshToken")
                .antMatchers(HttpMethod.GET,"/monitoring/endpoints")
        }
    }

    private fun checkPort(port: Int): RequestMatcher? {
        return RequestMatcher { request: HttpServletRequest -> port == request.localPort }
    }
}