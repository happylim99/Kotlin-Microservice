package com.sean.gateway.config

import io.netty.handler.logging.LogLevel
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.netty.http.client.HttpClient
import reactor.netty.transport.logging.AdvancedByteBufFormat

//@Configuration
class CustomLogLevel {

//    @Bean
//    fun httpClient(): HttpClient? {
//        return HttpClient.create().wiretap(
//            "LoggingFilter",
//            LogLevel.INFO,
//            AdvancedByteBufFormat.TEXTUAL)
//    }
}