package com.sean.apigateway

import com.sean.apigateway.config.RibbonConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.netflix.ribbon.RibbonClient
import org.springframework.cloud.netflix.zuul.EnableZuulProxy
import org.springframework.retry.annotation.EnableRetry

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
//@EnableRetry
//@RibbonClient(name = "user", configuration = [RibbonConfig::class])
class ApigatewayApplication

fun main(args: Array<String>) {
	runApplication<ApigatewayApplication>(*args)
}
