package com.sean.auth.config

import com.sean.auth.apicomm.FeignErrorDecoder
import com.sean.auth.apicomm.UserServiceClientImpl
import com.sean.auth.apicomm.UserServiceFallback
import com.sean.auth.apicomm.UserServiceFallbackFactory
import feign.Logger
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class BeanConfig {

    @Bean
    @LoadBalanced
    fun getRestTemplate() = RestTemplate()

    @Bean
    fun feignLogger() = Logger.Level.FULL

    @Bean
    fun feignErrorDecoder() = FeignErrorDecoder()

    @Bean
    fun userServiceClientImpl() = UserServiceClientImpl()

    @Bean
    fun userServiceFallbackFactory() = UserServiceFallbackFactory()
}