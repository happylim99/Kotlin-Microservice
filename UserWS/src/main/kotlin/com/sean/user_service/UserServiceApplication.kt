package com.sean.user_service

import com.sean.user_service.config.GmailConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient


@SpringBootApplication(scanBasePackages= ["com.sean"])
@EnableConfigurationProperties(GmailConfig::class)
@EnableEurekaClient
class UserServiceApplication

fun main(args: Array<String>) {
	runApplication<UserServiceApplication>(*args)
}





