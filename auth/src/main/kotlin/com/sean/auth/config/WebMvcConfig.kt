package com.sean.auth.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.method.HandlerTypePredicate
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig : WebMvcConfigurer {

//    override fun configurePathMatch(configurer: PathMatchConfigurer) {
//        configurer.addPathPrefix("auth",
//            HandlerTypePredicate.forAnnotation(RestController::class.java))
//    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
//            .allowedOrigins("http://localhost:4200")
            .allowedOrigins("*")
            .allowedMethods("*")
//            .allowedHeaders("header1", "header2", "header3")
//            .exposedHeaders("header1", "header2")
//            .allowCredentials(false).maxAge(3600)
    }
}