package com.sean.apigateway.config

import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.cglib.proxy.*
import org.springframework.cloud.netflix.zuul.filters.RouteLocator
import org.springframework.cloud.netflix.zuul.web.*
import org.springframework.context.annotation.*

/**
 * Fix for Zuul configuration with Spring Boot 2.5.x + Zuul - "NoSuchMethodError: ErrorController.getErrorPath()"
 */
//@Configuration
class ZuulSpringWorkaround {

    @Bean
    fun zuulPostProcessor(locator: RouteLocator, controller: ZuulController, errorController: ErrorController?) =
        object : BeanPostProcessor {

            override fun postProcessAfterInitialization(bean: Any, beanName: String) =
                if (errorController != null && bean is ZuulHandlerMapping) {
                    val constructorTypes = ZuulHandlerMapping::class.java.constructors[0].parameterTypes
                    Enhancer().apply {
                        setSuperclass(ZuulHandlerMapping::class.java)
                        setCallbackFilter { if ("lookupHandler" == it.name) 0 else 1 }
                        setCallbacks(arrayOf(MethodInterceptor { target, _, args, proxy ->
                            if ("/error" == args[0]) null else proxy.invokeSuper(target, args)
                        }, NoOp.INSTANCE))
                    }.create(constructorTypes, arrayOf(locator, controller))
                } else bean
        }
}