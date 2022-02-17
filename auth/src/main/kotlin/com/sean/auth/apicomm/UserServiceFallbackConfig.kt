package com.sean.auth.apicomm

import feign.Feign
//import io.github.resilience4j.circuitbreaker.CircuitBreaker
//import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
//import io.github.resilience4j.feign.FeignDecorators
//import io.github.resilience4j.feign.Resilience4jFeign
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Scope

class UserServiceFallbackConfig {

//    private val registry: CircuitBreakerRegistry? = null
//    private val userServiceFallback: UserServiceFallback? = null
//
//    @Bean
//    @Scope("prototype")
//    fun feignBuilder(): Feign.Builder? {
//        val circuitBreaker: CircuitBreaker = registry!!.circuitBreaker("user-ws")
//        val decorators = userServiceFallback?.let {
//            FeignDecorators.builder()
//                .withCircuitBreaker(circuitBreaker)
//                .withFallback(it)
//                .build()
//        }
//        return Resilience4jFeign.builder(decorators!!)
//    }
}