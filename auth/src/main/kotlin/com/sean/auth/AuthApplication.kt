package com.sean.auth

import com.sean.auth.config.GmailConfig
import com.sean.auth.data.DataFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.ApplicationContext
import org.springframework.context.event.EventListener
import org.springframework.web.server.adapter.WebHttpHandlerBuilder.applicationContext


@SpringBootApplication(scanBasePackages= ["com.sean"])
@EnableConfigurationProperties(GmailConfig::class)
@EnableEurekaClient
@EnableFeignClients
class AuthApplication

fun main(args: Array<String>) {
	runApplication<AuthApplication>(*args)
}





