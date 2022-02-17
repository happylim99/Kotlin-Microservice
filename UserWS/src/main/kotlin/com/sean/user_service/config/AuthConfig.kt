package com.sean.user_service.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class AuthConfig {

    @Bean
    fun bcrypt() = BCryptPasswordEncoder()

    @Bean
    fun mapper() = ObjectMapper()

}